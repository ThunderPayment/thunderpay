/**
 * @file MutableAccountData
 * @author Krisna Pranav
 * @brief Mutable Account Data
 * @version 1.0
 * @date 2025-03-19
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.account.api;

import java.util.UUID;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.thunderpay.billing.catalog.api.Currency;

public interface MutableAccountData extends AccountData {

    public void setExternalKey(String externalKey);

    public void setEmail(String email);

    public void setName(String name);

    public void setFirstNameLength(int firstNameLength);

    public void setCurrency(Currency currency);

    public void setBillCycleDayLocal(int billCycleDayLocal);

    public void setPaymentMethodId(UUID paymentMethodId);

    public void setReferenceTime(DateTime referenceTime);

    public void setTimeZone(DateTimeZone timeZone);

    public void setLocale(String locale);

    public void setAddress1(String address1);

    public void setAddress2(String address2);

    public void setCompanyName(String companyName);

    public void setCity(String city);

    public void setStateOrProvince(String stateOrProvince);

    public void setCountry(String country);

    public void setPostalCode(String postalCode);

    public void setPhone(String phone);

    public void setNotes(String notes);

    public void setIsMigrated(boolean isMigrated);

    public void setParentAccountId(UUID parentAccountId);

    public void setIsPaymentDelegatedToParent(boolean isPaymentDelegatedToParent);

}