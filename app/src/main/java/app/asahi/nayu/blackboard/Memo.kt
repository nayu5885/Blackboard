package app.asahi.nayu.blackboard

import android.graphics.Paint
import io.realm.RealmObject

open class Memo (
    open var content: String?="",
    open var paint: String?=""
):RealmObject()