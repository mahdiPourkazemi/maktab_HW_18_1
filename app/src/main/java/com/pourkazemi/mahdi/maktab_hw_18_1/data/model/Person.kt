package com.pourkazemi.mahdi.maktab_hw_18_1.data.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose

@Entity(tableName = "person")
data class Person(
    @PrimaryKey
    @Expose(serialize = false , deserialize = true)
    val _id: String,
    val firstName: String,
    val lastName: String,
    val nationalCode: String,
    val hobbies: List<String?>,
    val image: String?
    /*@ColumnInfo(defaultValue = "null",typeAffinity = ColumnInfo.BLOB)*/
) : Parcelable{
    @Ignore
    private val fullName: String = firstName + lastName
    @Ignore
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "Id Not Found",
        parcel.readString() ?: "First Name Not Found",
        parcel.readString() ?: "Last Name Not Found",
        parcel.readString() ?: "National Code Error",
        parcel.createStringArrayList()?.toList() ?: listOf("I have not any Hobbies"),
        parcel.readString()
    )

    @Ignore
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_id)
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeString(nationalCode)
        parcel.writeStringList(hobbies)
        parcel.writeString(image)
    }

    @Ignore
    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Person> {
        override fun createFromParcel(parcel: Parcel): Person {
            return Person(parcel)
        }

        override fun newArray(size: Int): Array<Person?> {
            return arrayOfNulls(size)
        }
    }
}
//parceable
//Transient
//null list solved with class