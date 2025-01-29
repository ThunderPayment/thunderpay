/**
 * @file UriBuilderExtensions.cs
 * @author Krisna Pranav
 * @brief Uri Builder Extensions
 * @version 1.0
 * @date 2024-01-19
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

using System;
using System.Linq;
using System.Text;
using System.Web;
using System.Collections.Generic;
using System.Collections.Specialized;

public static class UriBuilderExtensions
{
    public static UriBuilder SetQueryParam(this UriBuilder uri, string key, string value)
    {
        var collection = uri.ParseQuery();

        collection.Set(key, value);

        string query = collection
            .AsKeyValuePairs()
            .ToConcatenatedString(pair =>
                pair.Key == null
                ? System.Uri.EscapeDataString(pair.Value)
                : System.Uri.EscapeDataString(pair.Key) + "=" + System.Uri.EscapeDataString(pair.Value), "&");


        uri.Query = query;

        return uri;
    } // public static UriBuilder SetQueryParam(this UriBuilder uri, string key, string value)
} // public static class UriBuilderExtensions