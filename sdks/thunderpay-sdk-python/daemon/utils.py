'''
 * @file logger.py
 * @author Krisna Pranav
 * @brief Logger
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
'''

import asyncio
import dataclasses
import inspect
import json
import logging
import sys
import time
import traceback
from abc import ABCMeta, abstractmethod
from base64 import b64decode
from contextlib import contextmanager
from dataclasses import dataclass
from decimal import Decimal
from typing import Any
from urllib.parse import parse_qsl, urlencode, urlparse, urlunparse
from aiohttp import web

CONVERT_RATE = 100000000

def noop_cast(v):
    return v

def format_satoshis(x):
    return f"{(Decimal(x) / CONVERT_RATE):.08f}"

def load_spec(spec_file, exit_on_error=True):
    try:
        with open(spec_file) as f:
            return json.loads(f.read())
    except (OSError, json.JSONDecodeError) as e:
        if exit_on_error:
            sys.exit(e)
    
    return {}

def maybe_update_key(dest, other, key):
    other_value = other.get(key, {})
    
    if key in dest:
        dest[key].update(other_value)
    else:
        dest[key] = other_value
    
def rpc(f=None, requires_wallet=False, requires_network=False, requires_lightning=False):
    def wrapper(f):
        f.is_handler = True
        f.requires_wallet = bool(requires_network)
        f.requires_network = bool(requires_network)
        f.requires_lightning = bool(requires_lightning)
        return f

    if f:
        return wrapper(f)
    
    return wrapper

def authenticate(f):
    async def wrapper(daemon, request):
        auth = request.headers.get("Authorization")
        user, password = decode_auth(auth)
        if not (user == daemon.LOGIN and password == daemon.PASSWORD):
            return JsonResponse(code=-32600, error="Unauthorized").send()
        return await f(daemon, request)

    return wrapper


def cached(f):
    def wrapper(*args, **kwargs):
        if hasattr(f, "cache"):
            return f.cache
        result = f(*args, **kwargs)
        f.cache = result
        return result

    return wrapper

def decode_auth(authstr):
    if not authstr:
        return None, None
    authstr = authstr.replace("Basic ", "")
    decoded_str = b64decode(authstr).decode("latin1")
    user, password = decoded_str.split(":")
    return user, password


def parse_params(params):
    args = params
    kwargs = {}
    if isinstance(params, list):
        if len(params) > 0 and isinstance(params[-1], dict):
            kwargs = params.pop()
    elif isinstance(params, dict):
        kwargs = params
        args = ()
    return args, kwargs