/**
 * @file APIException.cs
 * @author Krisna Pranav
 * @brief APIException
 * @version 1.0
 * @date 2024-01-29
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

using System;
using System.Net;
using System.Text;
using System.Linq;
using System.Reflection;
using Newtonsoft.Json.Linq;
using System.Threading.Tasks;
using System.Collections.Generic;
using System.Runtime.Serialization;

namespace ThunderPay.Exception
{
    public class APIException : HTTPException
    {
        public string additional
        {
            get; set;
        }

        public string category_code
        {
            get; set;
        }

        public string category_type
        {
            get; set;
        }

        public string description
        {
            get; set;
        }

        public Dictionary<string, string> extras
        {
            get; set;
        }

        public string request_id
        {
            get; set;
        }
    }
}