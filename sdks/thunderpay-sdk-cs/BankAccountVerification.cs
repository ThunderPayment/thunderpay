/**
 * @file BankAccountVerification.cs
 * @author Krisna Pranav
 * @brief Bank Account Verification
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
    public class BankAccountVerification : Resource
    {
        [JsonIgnore]
        public static string resource_href
        {
            get
            {
                return "/bank_account_verifications";
            }
        } // public static string resource_href

        [ResourceField]
        public int attempts
        {
            get; set;
        } // public int attempts 

        [ResourceField]
        public int attempts_remaining
        {
            get; set;
        } // public int attempts_remaining 

        [ResourceField]
        public string deposit_status
        {
            get; set;
        } // public string deposit_status 

        [ResourceField]
        public string verification_status
        {
            get; set;
        } // public string verification_status 

        [ResourceField(field = "bank_account_verifications.bank_account", link = true, serialize = false)]
        public BankAccount bank_account
        {
            get; set;
        } // public BankAccount bank_account 
    } // public class BankAccountVerification : Resource
} // namespace ThunderPay