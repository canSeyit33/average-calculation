package com.seyitcanbag.ortalamahesapla

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.widget.*
//import androidx.appcompat.app.AppCompatActivity


import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.new_lesson_layout.*
import kotlinx.android.synthetic.main.new_lesson_layout.view.*


class MainActivity : AppCompatActivity() {



    private val LESSON =
        arrayOf("Matematik", "Türkçe", "Fizik", "Kimya", "Biyoloji", "Tarih", "Edebiyat")
    var allLessons:ArrayList<Lessons> = ArrayList(5)


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        //Spinner Credit için beyaz textColor
        val adapter1: ArrayAdapter<*> = ArrayAdapter.createFromResource(
            this,
            R.array.allCredits, R.layout.spinner_item
        )
        adapter1.setDropDownViewResource(R.layout.spinner_dropdown_item)
        spinnerCredit.setAdapter(adapter1)

        //SpinnerLetter için beyaz textColor
        val adapter2: ArrayAdapter<*> = ArrayAdapter.createFromResource(
            this,
            R.array.allNotes, R.layout.spinner_item
        )
        adapter2.setDropDownViewResource(R.layout.spinner_dropdown_item)
        spinnerLetter.setAdapter(adapter2)


        var adapter =
            ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, LESSON)
        tvLesson.setAdapter(adapter)

        if (idLinearLayout.childCount == 0) {
            calculateButton.visibility = View.INVISIBLE
        } else {
            calculateButton.visibility = View.VISIBLE
        }

        buttonAdd.setOnClickListener {

            if (!tvLesson.text.isNullOrEmpty()) {

                var inflater = LayoutInflater.from(this)
                var newLessonView = inflater.inflate(R.layout.new_lesson_layout, null)
                //null olmasının sebebei nereye ekleyeceğimizi daha sonra elle yapacağız

                var lessonName = tvLesson.text.toString()
                var sCredit = spinnerCredit.selectedItem.toString()
                var sLetter = spinnerLetter.selectedItem.toString()


                newLessonView.textViewName.setText(lessonName)
                newLessonView.newTv.setText(sCredit)
                newLessonView.newTv2.setText(sLetter)

                //Silme butonu tıklanması
                newLessonView.buttonRemove.setOnClickListener {
                    idLinearLayout.removeView(newLessonView)
                    if (idLinearLayout.childCount == 0) {
                        calculateButton.visibility = View.INVISIBLE
                    } else {
                        calculateButton.visibility = View.VISIBLE
                    }
                }

                idLinearLayout.addView(newLessonView)

                calculateButton.visibility = View.VISIBLE
                restartName()
            } else {

                //Toast.makeText(this, "Lütfen Ders Giriniz :", Toast.LENGTH_LONG).show()

            }

        }

    }



    fun restartName() {
        tvLesson.setText("")
        spinnerCredit.setSelection(0)
        spinnerLetter.setSelection(0)
    }
    fun spinnercreditToDouble(str:String) : Double{
        var result = 0.0
        when(str){
            "1 Kredi" -> result = 1.0
            "2 Kredi" -> result = 2.0
            "3 Kredi" -> result = 3.0
            "4 Kredi" -> result = 4.0
            "5 Kredi" -> result = 5.0
            "6 Kredi" -> result = 6.0
            "7 Kredi" -> result = 7.0
            "8 Kredi" -> result = 8.0
            "9 Kredi" -> result = 9.0
            "10 Kredi" -> result = 10.0
        }
        return result
    }
    fun spinnerLetterToDouble(str:String) : Double{
        var result = 0.0
        when(str){
            "AA" -> result = 4.0
            "BA" -> result = 3.5
            "BB" -> result = 3.0
            "CB" -> result = 2.5
            "CC" -> result = 2.0
            "DC" -> result = 1.5
            "DD" -> result = 1.0
            "FF" -> result = 0.0
        }
        return result
    }
    fun calculateAverage(view: View) {
        var sumCredit:Double = 0.0
        var sumNote:Double = 0.0
        for (i in 0 until idLinearLayout.childCount) {
            var tempChild = idLinearLayout.getChildAt(i)
            var tempLesson = Lessons(
                tempChild.textViewName.text.toString(),
                tempChild.newTv.text.toString(),
                tempChild.newTv2.text.toString()
            )
            allLessons.add(tempLesson)
            println("Liste : " +allLessons)

        }

        for (currentLesson in allLessons){

            sumNote += spinnercreditToDouble(currentLesson.lessonCredit) * spinnerLetterToDouble(currentLesson.lessonLetter)
            sumCredit += spinnercreditToDouble(currentLesson.lessonCredit)

        }
        fun Double.changeNumber(howManyDot: Int) = java.lang.String.format("%.${howManyDot}f", this)

        Toast.makeText(this,"Ortalama : " + (sumNote/sumCredit).changeNumber(2),Toast.LENGTH_LONG).show()
        allLessons.clear()
    }

}