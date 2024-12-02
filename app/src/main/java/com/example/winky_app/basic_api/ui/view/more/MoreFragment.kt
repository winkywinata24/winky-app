package com.example.winky_app.basic_api.ui.view.more

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.winky_app.databinding.BottomSheetLayoutBinding
import com.example.winky_app.databinding.FragmentMoreBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.chip.Chip
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class MoreFragment : Fragment() {
    private var _binding : FragmentMoreBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        val view = inflater.inflate(R.layout.fragment_more, container, false)
        _binding = FragmentMoreBinding.inflate(inflater, container, false)

        setupFAB(binding)

        return binding.root
    }

    private fun setupFAB(binding: FragmentMoreBinding) {
//        val fab: FloatingActionButton = view.findViewById(R.id.fab)
        binding.fab.setOnClickListener {
            showBottomSheetDialog()
        }
    }

    private fun showBottomSheetDialog() {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
//        val view = LayoutInflater.from(requireContext()).inflate(R.layout.bottom_sheet_layout, null)
        val binding = BottomSheetLayoutBinding.inflate(layoutInflater)
        bottomSheetDialog.setContentView(binding.root)
        bottomSheetDialog.show()

        initChips(binding)

        initDatePicker(binding)

        initSingleChoiceDialog(binding)
    }

    private fun initChips(binding: BottomSheetLayoutBinding) {
//        val chipGroup: ChipGroup = view.findViewById(R.id.chipGroup)

        val tags = listOf("Tag 1", "Tag 2", "Tag 3", "Tag 4", "Tag 5")
        for (tag in tags) {
            val chip = Chip(this.context).apply {
                text = tag
                isCheckable = true
            }
            binding.chipGroup.addView(chip)
        }
    }

    private fun initDatePicker(binding: BottomSheetLayoutBinding) {
        val datepicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Pilih Tanggal")
            .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
            .build()

//        val inputDate : TextInputEditText = view.findViewById(R.id.inputDate)
        binding.inputDate.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Material Design Dialog")
                .setMessage("Ini merupakan contoh penerapan dialog versi material design. Apakah anda ingin tetap menampilkan kalender?")
                .setNeutralButton("Batal") { dialog, which ->
                }
                .setNegativeButton("Tidak") { dialog, which ->
                }
                .setPositiveButton("Ya") { dialog, which ->
                    datepicker.show(parentFragmentManager, "MATERIAL_DATE_PICKER")
                }
                .show()
        }
        datepicker.addOnPositiveButtonClickListener { selection ->
            val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val selectedDate = dateFormat.format(Date(selection))
            binding.inputDate.setText(selectedDate)
        }
    }

    private fun initSingleChoiceDialog(binding: BottomSheetLayoutBinding) {
        val options = arrayOf("Pilihan 1","Pilihan 2","Pilihan 3","Pilihan 4")
        val selectedOption = 0
//        val inputOthers : TextInputEditText = view.findViewById(R.id.inputOthers)

        binding.inputOthers.setOnClickListener {
            showSingleChoiceDialog(options, selectedOption) {choice ->
                binding.inputOthers.setText(options[choice])
            }
        }
    }

    private fun showSingleChoiceDialog(
        options: Array<String>,
        checkedItem: Int,
        onChoiceSelected: (Int) -> Unit
    ) {
        var tempSelectedOption = checkedItem
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Pilih Opsi")
            .setSingleChoiceItems(options, checkedItem) { dialog, which ->
                tempSelectedOption = which
            }
            .setPositiveButton("Ok") { dialog, _ ->
                onChoiceSelected(tempSelectedOption)
                dialog.dismiss()
            }
            .setNegativeButton("Batal") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}