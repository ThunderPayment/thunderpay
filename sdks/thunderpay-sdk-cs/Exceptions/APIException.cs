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

namespace ThunderPay.Exceptions
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

        public APIException() { }

        public APIException(
            HttpWebResponse response,
            Dictionary<string, object> responsePayload)
        {
            additional = responsePayload.ContainsKey("additional") ? (string)responsePayload["additional"] : null;
            category_code = responsePayload.ContainsKey("category_code") ? (string)responsePayload["category_code"] : null;
            category_type = responsePayload.ContainsKey("category_type") ? (string)responsePayload["category_type"] : null;
            description = responsePayload.ContainsKey("description") ? (string)responsePayload["description"] : null;

            if (responsePayload.ContainsKey("extras") && responsePayload["extras"] != null)
                extras = ((JObject)responsePayload["extras"]).ToObject<Dictionary<string, string>>();

            request_id = responsePayload.ContainsKey("request_id") ? (string)responsePayload["request_id"] : null;
            status = responsePayload.ContainsKey("status") ? (string)responsePayload["status"] : null;
            status_code = Convert.ToInt16(responsePayload["status_code"]);
        }
    }
}