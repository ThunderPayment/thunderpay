/**
 * @file FundingInstrument.cs
 * @author Krisna Pranav
 * @brief Funding Instrument
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
    public abstract class FundingInstrument : Resource
    {
        [ResourceField(serialize = false)]
        public bool can_credit
        {
            get; set;
        } // public bool can_credit

        [ResourceField(serialize = false)]
        public bool can_debit
        {
            get; set;
        } // public bool can_debit

        [ResourceField(serialize = false)]
        public string bank_name
        {
            get; set;
        } // public string bank_name

        [ResourceField(serialize = false)]
        public string fingerprint
        {
            get; set;
        } // public string fingerprint
    } // public abstract class FundingInstrument : Resource
} // namespace ThunderPay