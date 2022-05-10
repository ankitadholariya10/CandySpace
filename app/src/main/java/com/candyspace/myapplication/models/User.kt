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
    val badge_counts: @RawValue BadgeCounts?,
    val collectives: @RawValue List<Collectives>?,
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
    var topTag: String = ""
    val userName
        get() = "$user_id $display_name"
    val badge
        get() =
            "Bonze -> " + badge_counts?.bronze + "\n" +
                    "Gold -> " + badge_counts?.gold + "\n" +
                    "Silver ->" + badge_counts?.silver + "\n"
    val createdAt
        get() = getDate(creation_date)

    fun getReputations(): String {
        Log.e("Reputation ==>", "get " + reputation + display_name)
        return reputation.toString()
    }

    fun getTopTags(): String {
        Log.e("Collectivs", collectives.toString())
        var tags = ""
        if (!collectives.isNullOrEmpty()) {

            collectives.forEach {
                if (tags.isNullOrEmpty())
                    tags = it.collective!!.name + "/n"
                else
                    tags = tags + "," + "\n" + it.collective!!.name
            }
        }else {
            tags= "No Tags Found"
        }
        return tags
    }

    @Parcelize
    data class Collectives(
        val collective: Collective?,
        val role: String
    ) : Parcelable

    @Parcelize
    data class Collective(
        val tags: List<String>?,
        val name: String
    ) : Parcelable

    data class BadgeCounts(
        val bronze: Int,
        val gold: Int,
        val silver: Int
    )

}