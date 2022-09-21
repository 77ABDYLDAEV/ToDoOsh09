package com.example.todoosh09

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todoosh09.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
lateinit var binding: FragmentHomeBinding
var taskAdapter =TaskAdapter(arrayListOf())
override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClicker()
        arguments?.let {
            var list = arrayListOf<TaskModel>()
            val model = it.getSerializable("model") as TaskModel
            list.add(model)
            taskAdapter = TaskAdapter(list)
        binding.recycleview.adapter = taskAdapter
        }
    }

    private fun initClicker() {
        binding.createfab.setOnClickListener {
            CreateTaskFragment().show(requireActivity().supportFragmentManager, "")
        }
    }
}