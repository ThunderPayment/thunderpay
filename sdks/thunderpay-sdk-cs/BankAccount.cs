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

        [ResourceField(field = "bank_accounts.bank_account_verification", link = true, serialize = false)]
        public BankAccountVerification verification
        {
            get; set;
        } // public BankAccountVerification verification 

        [ResourceField(field = "bank_accounts.bank_account_verifications", link = true, serialize = false)]
        public BankAccountVerification.Collection verifications
        {
            get; set;
        } // public BankAccountVerification.Collection verifications 

        [ResourceField(field = "bank_accounts.credits", link = true, serialize = false)]
        public Credit.Collection credits
        {
            get; set;
        } // public Credit.Collection credits 

        [ResourceField(field = "bank_accounts.customer", link = true)]
        public Customer customer
        {
            get; set;
        } // public Customer customer 

        [ResourceField(field = "bank_accounts.debits", link = true, serialize = false)]
        public Debit.Collection debits
        {
            get; set;
        } // public Debit.Collection debits 
    } // public class BankAccount : FundingInstrument
} // namespace ThunderPay