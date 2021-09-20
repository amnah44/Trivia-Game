package com.example.triviatask.utils

import android.R
import android.view.View
import android.widget.*
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.recyclerview.widget.RecyclerView
import com.example.triviatask.model.data.apiCategory.TriviaCategory
import com.example.triviatask.ui.base.BaseAdapter
import com.mcdev.quantitizerlibrary.HorizontalQuantitizer
import com.mcdev.quantitizerlibrary.QuantitizerListener


@BindingAdapter(value = ["app:entries"])
fun  setEntries(view: Spinner, entries: List<TriviaCategory>?) {
    if (entries != null) {
        ArrayAdapter(view.context,
            R.layout.simple_spinner_dropdown_item, (entries.map { it.name }))
            .also {
                it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                view.adapter = it }
    }
}


@BindingAdapter(value = ["app:selectedValue"])
fun selectedItem(view: Spinner, item: String?) {
    if (view.selectedItem?.toString() != item){
        view.setSelection(view.getSelectedIndex(item))
    }
}

@InverseBindingAdapter(attribute = "selectedValue", event = "SpinnerOnItemSelected")
fun captureSelectedValue(view: Spinner): String? {
    return view.selectedItem.toString()
}

@BindingAdapter("SpinnerOnItemSelected")
fun setSelectedListener(view: Spinner, changeListener: InverseBindingListener) {
    view.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            changeListener.onChange()
        }
        override fun onNothingSelected(p0: AdapterView<*>?) {}
    }
}



@BindingAdapter(value = ["selectRadioButtonIndex"])
fun setSelectedChildIndex(view: RadioGroup, index: Int) {

    if (view.checkedRadioButtonId != index){
        view.setSelectedIndex(index)
    }
}

@InverseBindingAdapter(attribute = "selectRadioButtonIndex", event = "selectRadioButtonIndex")
fun getChildIndex(view: RadioGroup): Int? {
    return view.getSelectedIndex()
}

@BindingAdapter("selectRadioButtonIndex")
fun setRadioListener(view: RadioGroup, attChange: InverseBindingListener) {
    view.setOnCheckedChangeListener { group, checkedId ->
        attChange.onChange()
    }
}



@BindingAdapter(value = ["minValue"])
fun setNumberPikerMinValue(view: HorizontalQuantitizer, value: Int?) {
    value?.let { view.minValue = it }
}

@BindingAdapter(value = ["maxValue"])
fun setNumberPikerMaxValue(view: HorizontalQuantitizer, value: Int?) {
    value?.let { view.maxValue = it }
}


@BindingAdapter(value = ["value"])
fun setPikerNumber(view: HorizontalQuantitizer, value: Int?) {
    if (view.value != value){
        value?.let { view.value = it }
    }
}

@InverseBindingAdapter(attribute = "value", event = "pikerNumberChangeEvent")
fun getPikerNumber(view: HorizontalQuantitizer): Int? {
    return view.value
}

@BindingAdapter("pikerNumberChangeEvent")
fun setPikerListener(view: HorizontalQuantitizer, attChange: InverseBindingListener) {
    view.setQuantitizerListener(object : QuantitizerListener {
        override fun onDecrease() {
            attChange.onChange()
        }
        override fun onIncrease() {
            attChange.onChange()
        }
    })
}

@BindingAdapter(value = ["app:ifWinner"])
fun checkWinner(view: TextView, valueScore: Int){
    when{
        valueScore < 5 -> { view.text = "You Lost" }
        else -> {  view.text = "You Win"  }
    }
}


@BindingAdapter(value = ["app:ifCongrats"])
fun checkCongrats(view: TextView, valueScore: Int){
    when{
        valueScore < 5 -> { view.text = "Hard Luck!" }
        else -> {  view.text = "Congrats!"  }
    }
}


@BindingAdapter(value = ["app:items"])
fun <T> setRecyclerItems(view: RecyclerView, items:List<T>?) =
    (view.adapter as BaseAdapter<T>?)?.let {
        if(items != null)
            it.setItem(items)
        else
            it.setItem(emptyList())
    }





