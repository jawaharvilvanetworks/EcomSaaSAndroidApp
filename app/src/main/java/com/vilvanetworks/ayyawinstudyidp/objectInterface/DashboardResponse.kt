package com.vilvanetworks.ayyawinstudyidp.objectInterface

data class DashboardResponse(
    val statuscode: Int,
    val success: Boolean,
    val message: String,
    val data: DashboardData
)

data class DashboardData(
    val day: DayData,
    val week: WeekData,
    val month: MonthData,
    val latest_orders: List<LatestOrder>,
    val latest_customers: List<LatestCustomer>,
    val orders: OrderSummary,
    val customers: CustomerSummary
)

data class DayData(
    val day: String,
    val reports: String // JSON string that needs to be parsed separately
)

data class WeekData(
    val week: String,
    val reports: List<String> // Each report is a JSON string to be parsed separately
)

data class MonthData(
    val month: String,
    val reports: List<String> // Each report is a JSON string to be parsed separately
)

data class LatestOrder(
    val storeid: Int,
    val order_status: Int,
    val payment_status: String,
    val order_id: String,
    val totalamt: Int,
    val record_date: String,
    val storename: String
)

data class LatestCustomer(
    val storeid: Int,
    val name: String?,
    val mobile: String?,
    val last_login: String,
    val created_at: String,
    val id: Int,
    val last_ordered: String?,
    val storename: String
)

data class OrderSummary(
    val total_count: Int,
    val success_count: Int,
    val total_value: String,
    val success_value: String
)

data class CustomerSummary(
    val total: Int,
    val active: Int
)
