'''
/**
 * @file pluginmigrate.py
 * @author Krisna Pranav
 * @brief plugin migrate
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */
'''

import os
import sys
from alembic.config import CommandLine, Config
from api.plugins import PluginsManager

sys.path.insert(0, ".")

if len(sys.argv) < 3:
    sys.exit("")

plugin_name = sys.argv[1]
manager = PluginsManager()

if plugin_name not in manager.plugins:
    sys.exit(f"[LOG]: {plugin_name} does not exists!...")
    
cmd = CommandLine(prog="pluginmigrate")
plugin = manager.plugins[plugin_name]

# configs
config = Config("alembic.ini")
config.set_main_option("plugin_name", plugin.name)
config.set_main_option("version_locations", os.path.join(plugin.path, "versions"))

options = cmd.parser.parse_args(sys.argv[2:])

cmd.run_cmd(config, options)