/**
 * @file Currency.java
 * @author Krisna Pranav
 * @brief Currency
 * @version 1.0
 * @date 2025-01-20
 *
 * @copyright Copyright (c) 2024 ThunderPayment Developers, Krisna Pranav
 *
 */

package org.thunderpay.billing.catalog.api;
import jakarta.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum Currency {
    AED("د.إ"),
    AFN("؋"),
    ALL("L"),
    AMD("դր."),
    ANG("ƒ"),
    AOA("Kz"),
    ARS("$"),
    AUD("A$"),
    AWG("ƒ"),
    AZN("ман"),
    BAM("KM"),
    BBD("Bds$"),
    BDT("৳"),
    BGN("лв"),
    BHD(".د.ب"),
    BIF("FBu"),
    BMD("BD$"),
    BND("B$"),
    BOB("Bs."),
    BRL("R$"),
    BSD("B$"),
    BTN("Nu."),
    BWP("P"),
    BYR("Br"),
    BZD("BZ$"),
    CAD("Can$"),
    CDF("FC"),
    CHF("SFr"),
    CLP("CLP$"),
    CNY("元/圆"),
    COP("COL$"),
    CRC("₡"),
    CUC("CUC$"),
    CUP("$MN"),
    CVE("Esc"),
    CZK("Kč"),
    DJF("DF"),
    DKK("Dkr"),
    DOP("RD$"),
    DZD("دج"),
    EGP("ج.م."),
    ERN("Nfk"),
    ETB("Br"),
    EUR("€"),
    FJD("FJ$"),
    FKP("FK£"),
    GBP("£"),
    GEL("ლ"),
    GGP("£"),
    GHS("GH₵"),
    GIP("£"),
    GMD("D"),
    GNF("GFr"),
    GTQ("Q"),
    GYD("G$"),
    HKD("HK$"),
    HNL("L"),
    HRK("kn"),
    HTG("G"),
    HUF("Ft"),
    IDR("Rp"),
    ILS("₪"),
    IMP("M£"),
    INR("₹"),
    IQD("د.ع"),
    IRR("﷼"),
    ISK("Íkr"),
    JEP("£"),
    JMD("J$"),
    JOD("JD"),
    JPY("¥"),
    KES("Ksh"),
    KGS("сом"),
    KHR("៛"),
    KMF("CF"),
    KPW("₩"),
    KRW("₩"),
    KWD("د.ك"),
    KYD("CI$"),
    KZT("₸"),
    LAK("₭"),
    LBP("ل.ل"),
    LKR("රු"),
    LRD("L$"),
    LSL("M"),
    LTL("Lt"),
    LVL("Ls"),
    LYD("ل.د"),
    MAD("د.م."),
    MDL("L"),
    MGA("Ar"),
    MKD("ден"),
    MMK("K"),
    MNT("₮"),
    MOP("MOP$"),
    MRO("UM"),
    MUR("₨"),
    MVR("Rf."),
    MWK("MK"),
    MXN("Mex$"),
    MYR("RM"),
    MZN("MT"),
    NAD("N$"),
    NGN("₦"),
    NIO("C$"),
    NOK("kr"),
    NPR("रू"),
    NZD("NZ$"),
    OMR("ر.ع"),
    PAB("B/."),
    PEN("S/."),
    PGK("K"),
    PHP("₱"),
    PKR("₨"),
    PLN("zł"),
    PYG("₲"),
    QAR("ر.ق"),
    RON("leu"),
    RSD("Дин"),
    RUB("p."),
    RWF("FRw"),
    SAR("ر.س"),
    SBD("SI$"),
    SCR("SRe"),
    SDG("ج.س."),
    SEK("kr"),
    SGD("S$"),
    SHP("£"),
    SLL("Le"),
    SOS("Sh.So."),
    SPL("SPL"),
    SRD("SRD"),
    STD("Db"),
    SVC("₡"),
    SYP("ل.س"),
    SZL("L"),
    THB("฿"),
    TJS("SM"),
    TMT("m"),
    TND("د.ت"),
    TOP("T$"),
    TRY("₺"),
    TTD("TT$"),
    TVD("$"),
    TWD("NT$"),
    TZS("TSh"),
    UAH("₴"),
    UGX("USh"),
    USD("US$"),
    UYU("$U"),
    UZS("som"),
    VEF("Bs."),
    VND("₫"),
    VUV("VT"),
    WST("WS$"),
    XAF("FCFA"),
    XCD("EC$"),
    XDR("SDR"),
    XOF("CFA"),
    XPF("₣"),
    YER("﷼"),
    ZAR("R"),
    ZMW("ZK"),
    ZWD("Z$"),

    BTC("Ƀ");

    private final String symbol;

    Currency(final String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public static Currency getDefaultCurrency() {
        return Currency.USD;
    }

    public static Currency fromCode(final String currencyCode) {
        for (final Currency currency : Currency.values()) {
            if (currency.toString().equals(currencyCode)) {
                return currency;
            }
        }
        return getDefaultCurrency();
    }
}