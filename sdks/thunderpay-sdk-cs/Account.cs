/**
 * @file Account.cs
 * @author Krisna Pranav
 * @brief Account functionalities
 * @version 1.0
 * @date 2024-01-19
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

using System;
using System.Linq;
using System.Text;
using System.Collections.Generic;
using System.Threading.Tasks;
using Newtonsoft.Json;

namespace ThunderPay
{
    public class Account : FundingInstrument
    {
        [JsonIgnore]
        public static string resource_href
        {
            get
            {
                return "/accounts";
            }
        }
    }
}