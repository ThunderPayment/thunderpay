/**
 * @file Resource.cs
 * @author Krisna Pranav
 * @brief Resource funcs
 * @version 1.0
 * @date 2024-01-19
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

using System;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Collections;
using Newtonsoft.Json;
using System.Dynamic;
using System.Reflection;
using System.Collections.Generic;
using System.Runtime.Serialization;

namespace ThunderPay
{
    public abstract class Resource
    {
        [ResourceField(seialize = false)]
        public string href
        {
            get; set;
        }

        [ResourceField(serialize = false)]
        public string id
        {
            get; set;
        }

        [ResourceField]
        public Dictionary<string, string> links
        {
            get; set;
        }

        public Resource() { }

        public void Save<T>()
        {
            dynamic res = null;

            if (this.href != null)
            {
                res = Client.Put<T>(this.href, Serialize(this));
            }
            else
            {
                string href = this.GetType().GetProperties("resource_href").GetValue(this).ToString();
                res = Client.Post<T>(href, Serialize(this));
            }

            UpdateResource<T>(res);
        }
    }
}