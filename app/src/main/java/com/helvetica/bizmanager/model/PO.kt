package com.helvetica.bizmanager.model

data class PO(
    val id: String,
    val requested_by: String,
    val datum: String,
    val company: String,
    val company_code: String,
    val short_text: String,
    val po_quantity: String,
    val overall_limit: String,
    val gr_execution_date: String,
    val sbu: String,
    val status: String,
    val invoice: String,
    val gr: String,
    val manager: String
)
