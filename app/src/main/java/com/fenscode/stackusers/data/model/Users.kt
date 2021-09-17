package com.fenscode.stackusers.data.model

import com.google.gson.annotations.SerializedName

data class  Users(@SerializedName("items") var items: List<User>)

data class BadgeCounts(
    @SerializedName("bronze") var bronze: Int,
    @SerializedName("silver") var silver: Int,
    @SerializedName("gold") var gold: Int
)

data class User(
    @SerializedName("badge_counts") var badgeCounts: BadgeCounts,
    @SerializedName("is_employee") var isEmployee: Boolean,
    @SerializedName("last_access_date") var lastAccessDate: Int,
    @SerializedName("reputation_change_year") var reputationChangeYear: Int,
    @SerializedName("reputation_change_quarter") var reputationChangeQuarter: Int,
    @SerializedName("reputation_change_month") var reputationChangeMonth: Int,
    @SerializedName("reputation_change_week") var reputationChangeWeek: Int,
    @SerializedName("reputation_change_day") var reputationChangeDay: Int,
    @SerializedName("reputation") var reputation: Int,
    @SerializedName("creation_date") var creationDate: Int,
    @SerializedName("user_type") var userType: String,
    @SerializedName("user_id") var userId: Int,
    @SerializedName("link") var link: String,
    @SerializedName("profile_image") var profileImage: String,
    @SerializedName("display_name") var displayName: String
)
