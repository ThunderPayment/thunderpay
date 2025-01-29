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
        } // public FundingInstrument funding_instrument

        [ResourceField]
        public int amount
        {
            get; set;
        } // public int amount 

        [ResourceField]
        public string description
        {
            get; set;
        } // public string description 

        [ResourceField]
        public string appears_on_statement_as
        {
            get; set;
        } // public string appears_on_statement_as 

        [ResourceField(field = "settlements.destination", link = true)]
        public FundingInstrument destination
        {
            get; set;
        } // public FundingInstrument destination 

        [ResourceField(field = "settlements.source", link = true)]
        public FundingInstrument source
        {
            get; set;
        } // public FundingInstrument source 
    } // public class Settlement : Resource
} // namespace ThunderPay