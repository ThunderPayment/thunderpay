/**
 * @file HTTPException.cs
 * @author Krisna Pranav
 * @brief HTTP Exception
 * @version 1.0
 * @date 2024-01-29
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

using System;
using System.Net;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Collections.Generic;

namespace ThunderPay.Exception
{
    public class HTTPException : Exception
    {
        public long status_code
        {
            get; set;
        }

        public string status
        {
            get; set;
        }
    } // public class HTTPException : Exception
} // namespace ThunderPay.Exception