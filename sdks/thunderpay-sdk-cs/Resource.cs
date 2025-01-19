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
using System.Dynamic;
using System.Reflection;
using System.Collections.Generic;
using System.Threading.Tasks;
using System.Collections;
using Newtonsoft.Json;

namespace ThunderPay
{
    public abstract class Resource
    {
        [ResourceField(serialize = false)]
        public string href
        {
            get; set;
        } // public string href

        [ResourceField(serialize = false)]
        public string id
        {
            get; set;
        } // public string id

        [ResourceField]
        public Dictionary<string, string> links
        {
            get; set;
        } // public Dictionary<string, string> links

        [ResourceField]
        public Dictionary<string, string> meta
        {
            get; set;
        } // public Dictionary<string, string> meta

        [ResourceField(serialize = false)]
        public DateTime created_at
        {
            get; set;
        } // public DateTime created_at

        [ResourceField(serialize = false)]
        public DateTime updated_at
        {
            get; set;
        } // public DateTime updaetd_at

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
                string href = this.GetType().GetProperty("resource_href").GetValue(this).ToString();
                res = Client.Post<T>(href, Serialize(this));
            }

            UpdateResource<T>(res);
        } // public void Save<T>()

        public void Unstore()
        {
            Client.Delete(this.href);
        } // publid void Unstore()

        public static T Fetch<T>(string href)
        {
            return Client.Get<T>(href);
        } // public static T Fetch<T>(string href)

        public void Reload<T>()
        {
            dynamic res = Client.Get<T>(href);
            UpdateResource<T>(res);
        } // publid void Reload<T>()

        public void UpdateResource<T>(dynamic res)
        {
            Type resType = this.GetType();
            List<PropertyInfo> fields = resType.GetProperties().ToList();

            foreach (PropertyInfo f in fields)
            {
                string propName = f.Name;
                if (f.Name.Equals("resource_href"))
                    continue;
                PropertyInfo propToCopy = res.GetType().GetProperty(propName);
                object propValue = propToCopy.GetValue(res);

                f.SetValue(this, propValue);
            }
        } // public void UpdateResource<T>(dynamic res)

        public static string Serialize(object resource)
        {
            return JsonConvert.SerializeObject(resource,
                new JsonSerializerSettings
                {
                    NullValueHandling = NullValueHandling.Ignore,
                    ContractResolver = new AllPropertiesResolver()
                });
        } // public static string Serialize(object resource)
    } // public abstract class Resource
} // namespace ThunderPay