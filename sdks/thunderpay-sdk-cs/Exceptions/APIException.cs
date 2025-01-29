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

    }
}