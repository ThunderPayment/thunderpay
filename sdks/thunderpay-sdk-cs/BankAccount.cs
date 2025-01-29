/**
 * @file BankAccount.cs
 * @author Krisna Pranav
 * @brief Bank Account
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
using System.Collections.Generic;
using System.Threading.Tasks;

namespace ThunderPay
{
    public class BankAccount : FundingInstrument
    {
        [JsonIgnore]
        public static string resource_href
        {
            get
            {
                return "/bank_accounts";
            }
        } // public static string resource_href

        [ResourceField]
        public string account_type
        {
            get; set;
        } // public string account_type 

        [ResourceField]
        public string account_number
        {
            get; set;
        } // public string account_number 

        [ResourceField]
        public Dictionary<string, string> address
        {
            get; set;
        } // public Dictionary<string, string> address 

        [ResourceField]
        public string name
        {
            get; set;
        } // public string name 

        [ResourceField]
        public string routing_number
        {
            get; set;
        } // public string routing_number 
    } // public class BankAccount : FundingInstrument
} // namespace ThunderPay