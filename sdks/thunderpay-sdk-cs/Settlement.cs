/**
 * @file Settlement.cs
 * @author Krisna Pranav
 * @brief Settlement
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
    public class Settlement : Resource
    {
        [JsonIgnore]
        public static string resource_href
        {
            get
            {
                return "/settlements";
            }
        } // public static string resource_href

        [ResourceField]
        public FundingInstrument funding_instrument
        {
            get; set;
        }
    } // public class Settlement : Resource
} // namespace ThunderPay