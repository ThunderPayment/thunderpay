/**
 * @file ApiKey.cs
 * @author Krisna Pranav
 * @brief API Key
 * @version 1.0
 * @date 2024-01-29
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

using System;
using System.Linq;
using System.Text;
using Newtonsoft.Json;
using System.Threading.Tasks;
using System.Collections.Generic;

namespace ThunderPay
{
    public class ApiKey : Resource
    {
        [JsonIgnore]
        public static string resource_href
        {
            get
            {
                return "/api_keys";
            }
        } // public static string resource_href

        [ResourceField]
        public string secret
        {
            get; set;
        } // public string secret

        public ApiKey() { }
    } // public class ApiKey : Resource
} // namespace ThunderPay