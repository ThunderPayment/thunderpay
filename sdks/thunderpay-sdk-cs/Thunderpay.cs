/**
 * @file Thunderpay.cs
 * @author Krisna Pranav
 * @brief Thunderpay
 * @version 1.0
 * @date 2024-01-19
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
    public static class Thunderpay
    {
        public static string VERSION
        {
            get
            {
                return "1.0";
            }
        } // public static string VERSION

        public static string API_REVISION
        {
            get
            {
                return "1.0";
            }
        } // public static string API_REVISION 

        public static string API_URL
        {
            get
            {
                return "https://api.thunderpay.com";
            }
        } // public static string API_URL 
    } // public static class Thunderpay
}