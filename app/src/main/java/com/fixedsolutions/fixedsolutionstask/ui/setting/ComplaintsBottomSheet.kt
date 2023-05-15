package com.fixedsolutions.fixedsolutionstask.ui.setting

import android.app.Dialog
import android.content.res.Resources
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.fixedsolutions.fixedsolutionstask.R
import com.fixedsolutions.fixedsolutionstask.common.getString
import com.fixedsolutions.fixedsolutionstask.common.isEmpty
import com.fixedsolutions.fixedsolutionstask.common.showMessage
import com.fixedsolutions.fixedsolutionstask.data.model.ComplaintModel
import com.fixedsolutions.fixedsolutionstask.databinding.BottomSheetComplaintsBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class ComplaintsBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetComplaintsBinding
    private lateinit var viewModel: SettingVM
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetComplaintsBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(requireParentFragment())[SettingVM::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnSubmit.setOnClickListener {
                validate()
            }
        }
    }

    private fun validate() {
        binding.apply {
            when {
                etTitle.isEmpty() -> {
                    showMessage("fill title")
                }
                etDesc.isEmpty() -> {
                    showMessage("fill description")
                }
                else -> {
                    viewModel.addComplaintItem(
                        ComplaintModel(
                            title = etTitle.getString(),
                            description = etDesc.getString()
                        )
                    )
                    dismiss()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        val sheetContainer = requireView().parent as? ViewGroup ?: return
        sheetContainer.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return BottomSheetDialog(requireContext(), theme).apply {
            window?.setBackgroundDrawableResource(R.drawable.transparent)
            behavior.isFitToContents = false
            behavior.expandedOffset = 56.dp
            behavior.state = BottomSheetBehavior.STATE_EXPANDED
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.CustomBottomSheetDialogTheme)
    }

    private val Int.dp: Int
        get() = (this * Resources.getSystem().displayMetrics.density + 0.5f).toInt()


}