/**
 * @file AccountData.java
 * @author Krisna Pranav
 * @brief Account Data
 * @version 1.0
 * @date 2024-11-25
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.account.api;

import java.util.UUID;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.thunderpay.billing.catalog.api.Currency;

public interface AccountData {

    public String getExternalKey();

    public String getName();

    public Integer getFirstNameLength();

    public String getEmail();

    public Integer getBillCycleDayLocal();

    public Currency getCurrency();

    public UUID getPaymentMethodId();

    public DateTime getReferenceTime();

    public DateTimeZone getTimeZone();

    public String getLocale();

    public String getAddress1();

    public String getAddress2();

    public String getCompanyName();

    public String getCity();

    public String getStateOrProvince();

    public String getPostalCode();

    public String getCountry();

    public String getPhone();

    public Boolean isMigrated();

    public UUID getParentAccountId();

    public Boolean isPaymentDelegatedToParent();

    public String getNotes();
}