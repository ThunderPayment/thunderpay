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
    } // public class BankAccountVerification : Resource
} // namespace ThunderPay