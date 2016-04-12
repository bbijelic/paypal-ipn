package com.github.bbijelic.paypal.ipn.entity;

/**
 * Transaction type.
 *
 * @author Bojan Bijelic
 */
public class TransactionType {

	/**
	 * A dispute has been resolved and closed
	 */
	public final static String ADJUSTMENT = "adjustment";

	/**
	 * Payment received for multiple items; source is Express Checkout or the
	 * PayPal Shopping Cart.
	 */
	public final static String CART = "cart";

	/**
	 * Payment received for a single item; source is Express Checkout
	 */
	public final static String EXPRESS_CHECKOUT = "express_checkout";

	/**
	 * Payment sent using Mass Pay
	 */
	public final static String MASS_PAY = "masspay";

	/**
	 * Monthly subscription paid for Website Payments Pro, Reference
	 * transactions, or Billing Agreement payments
	 */
	public final static String MERCH_PMT = "merch_pmt";

	/**
	 * Billing agreement cancelled
	 */
	public final static String MP_CANCEL = "mp_cancel";

	/**
	 * Created a billing agreement
	 */
	public final static String MP_SIGNUP = "mp_signup";

	/**
	 * A new dispute was filed
	 */
	public final static String NEW_CASE = "new_case";

	/**
	 * Payment received; source is Website Payments Pro Hosted Solution.
	 */
	public final static String PRO_HOSTED = "pro_hosted";

	/**
	 * Recurring payment received
	 */
	public final static String RECURRING_PAYMENT = "recurring_payment";

	/**
	 * Recurring payment expired
	 */
	public final static String RECURRING_PAYMENT_EXPIRED = "recurring_payment_expired";

	/**
	 * Recurring payment failed
	 */
	public final static String RECURRING_PAYMENT_FAILED = "recurring_payment_failed";

	/**
	 * Recurring payment profile canceled
	 */
	public final static String RECURRING_PAYMENT_PROFILE_CANCEL = "recurring_payment_profile_cancel";

	/**
	 * Recurring payment profile created
	 */
	public final static String RECURRING_PAYMENT_PROFILE_CREATED = "recurring_payment_profile_created";

	/**
	 * Recurring payment skipped; it will be retried up to 3 times, 5 days apart
	 */
	public final static String RECURRING_PAYMENT_SKIPPED = "recurring_payment_skipped";

	/**
	 * Recurring payment suspended. This transaction type is sent if PayPal
	 * tried to collect a recurring payment, but the related recurring payments
	 * profile has been suspended.
	 */
	public final static String RECURRING_PAYMENT_SUSPENDED = "recurring_payment_suspended";

	/**
	 * Recurring payment failed and the related recurring payment profile has
	 * been suspended
	 */
	public final static String RECURRING_PAYMENT_SUSPENDED_DUE_TO_MAX_FAILED_PAYMENT = "recurring_payment_suspended_due_to_max_failed_payment";

	/**
	 * Payment received; source is the Send Money tab on the PayPal website
	 */
	public final static String SEND_MONEY = "send_money";

	/**
	 * Subscription canceled
	 */
	public final static String SUBSCR_CANCEL = "subscr_cancel";

	/**
	 * Subscription expired
	 */
	public final static String SUBSCR_EOT = "subscr_eot";

	/**
	 * Subscription payment failed
	 */
	public final static String SUBSCR_FAILED = "subscr_failed";

	/**
	 * Subscription modified
	 */
	public final static String SUBSCR_MODIFY = "subscr_modify";

	/**
	 * Subscription payment received
	 */
	public final static String SUBSCR_PAYMENT = "subscr_payment";

	/**
	 * Subscription started
	 */
	public final static String SUBSCR_SIGNUP = "subscr_signup";

	/**
	 * Payment received; source is Virtual Terminal
	 */
	public final static String VIRTUAL_TERMINAL = "virtual_terminal";

	/**
	 * Payment received; source is any of the following: Direct Credit Card
	 * (Pro) transaction, Buy Now, Donation or Smart Logo for eBay auctions
	 * button
	 */
	public final static String WEB_ACCEPT = "web_accept";

}
