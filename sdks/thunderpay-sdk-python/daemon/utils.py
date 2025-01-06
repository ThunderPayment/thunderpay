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

def get_exception_message(e):
    return traceback.format_exception_only(type(e), e)[-1].strip()


@contextmanager
def hide_logging_errors(enable):
    if enable:
        logging.disable(logging.ERROR)
    yield
    if enable:
        logging.disable(logging.NOTSET)
        
@dataclass
class JsonResponse:
    id: int | None = None
    code: int | None = None
    error: str | None = None
    result: Any | None = None

    def send(self):
        if self.result is not None and self.error is not None:
            raise ValueError(f"result={self.result} and error={self.error} cannot be both set")
        if self.error is not None:
            return self.send_error_response()
        return self.send_ok_response()

    def send_error_response(self):
        return web.json_response({"jsonrpc": "2.0", "error": {"code": self.code, "message": self.error}, "id": self.id})

    def send_ok_response(self):
        return web.json_response({"jsonrpc": "2.0", "result": self.result, "id": self.id})
    
async def periodic_task(self, process_func, interval):
    while self.running:
        start = time.time()
        try:
            await process_func()
        except Exception:
            if self.VERBOSE:
                print(traceback.format_exc())
        elapsed = time.time() - start
        await asyncio.sleep(max(interval - elapsed, 0))
        
class CastingDataclass:
    def __post_init__(self):
        for field in dataclasses.fields(self):
            value = getattr(self, field.name)
            if (
                not isinstance(value, field.type)
                and field.default is dataclasses.MISSING
                and field.default_factory is dataclasses.MISSING
            ):
                setattr(self, field.name, field.type(value))