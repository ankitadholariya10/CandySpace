package com.candyspace.myapplication.models

import android.os.Parcelable
import android.util.Log
import com.candyspace.myapplication.util.getDate
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

data class User(
    val has_more: Boolean,
    val items: List<Item>,
    val quota_max: Int,
    val quota_remaining: Int
)


@Parcelize
data class Item(
    val badge_counts: @RawValue BadgeCounts,
    val collectives: @RawValue List<Collective>,
    val creation_date: Long,
    val display_name: String,
    val is_employee: Boolean,
    val link: String,
    val location: String,
    val profile_image: String,
    val reputation: Int,
    val user_id: Int,
    val user_type: String,
    val website_url: String
) : Parcelable {
    val userName
        get() = "$user_id $display_name"
    val badge
        get() =
            "Bonze -> " + badge_counts.bronze + "\n" +
                    "Gold -> " + badge_counts.gold + "\n" +
                    "Silver ->" + badge_counts.silver + "\n"
    val createdAt
        get() = getDate(creation_date)

    fun getReputations(): String {
        Log.e("Reputation ==>", "get " + reputation + display_name)
        return reputation.toString()
    }

    fun getTopTags(): String {
        var tags = ""
        if (!collectives.isNullOrEmpty()) {
            collectives.forEach {
                tags = tags + "," + "\n" + it.collective.name
            }
        }
        return tags
    }

}