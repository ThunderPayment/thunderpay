/**
 * @file EnumerableExtensions.cs
 * @author Krisna Pranav
 * @brief Enumerable Extensions
 * @version 1.0
 * @date 2024-01-19
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

using System;
using System.Text;
using System.Collections.Generic;

public static class EnumerableExtensions
{
    public static string ToConcatenatedString<T>(this IEnumerable<T> source, Func<T, string> stringSelector)
    {
        return source.ToConcatenatedString(stringSelector, String.Empty);
    } // public static string ToConcatenatedString<T>(this IEnumerable<T> source, Func<T, string> stringSelector)

    public static string ToConcatenatedString<T>(this IEnumerable<T> source, Func<T, string> stringSelector, string separator)
    {
        var b = new StringBuilder();
        bool needsSeparator = false;

        foreach (var item in source)
        {
            if (needsSeparator)
            {
                b.Append(separator);
            }

            b.Append(stringSelector(item));
        }

        return b.ToString();
    } // public static string ToConcatenatedString<T>(this IEnumerable<T> source, Func<T, string> stringSelector, string separator)
}