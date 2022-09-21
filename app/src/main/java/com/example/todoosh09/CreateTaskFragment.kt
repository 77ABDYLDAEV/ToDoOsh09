package com.example.todoosh09

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.navigation.fragment.findNavController
import com.example.todoosh09.databinding.FragmentCreateTaskBinding
import com.example.todoosh09.databinding.RegularDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS

class CreateTaskFragment : BottomSheetDialogFragment() {

    lateinit var binding:FragmentCreateTaskBinding
    var task=""
    var date=""
    var regular=""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =  FragmentCreateTaskBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClicker()
    }
    private fun showRegularDialog() {
        val dialog = Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        val binding = RegularDialogBinding.inflate(layoutInflater)
        dialog.setContentView(binding.root)
        binding.everyDayBtn.setOnClickListener{
            regular = binding.everyDayBtn.text.toString()
            this.binding.regularBtn.text = regular
            dialog.dismiss()
            binding.cancelBtn.setOnClickListener{}
        }
        dialog.show()

    }

    @Suppress("SetTextI18n")
    private fun initClicker() {
        with(binding) {
            applyBtn.setOnClickListener{
            val bundle = Bundle()
                bundle.putSerializable("model", TaskModel(taskEd.text.toString(),date,regular))
                findNavController().navigate(R.id.homeFragment, bundle)
            }
            regularBtn.setOnClickListener{
                showRegularDialog()
            }
            dateBtn.setOnClickListener {
                val c = Calendar.getInstance()
                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)
                val day = c.get(Calendar.DAY_OF_MONTH)
                val dpd = DatePickerDialog(requireActivity(), R.style.DialogTheme, DatePickerDialog.OnDateSetListener
                { view, year, monthOfYear, dayOfMonth ->
                    dateBtn.text = "$dayOfMonth. ${monthOfYear + 1}.  $year"
                    date = dateBtn.text.toString()
                }, year, month, day)
                dpd.show()
            }
        }
    }
}
