package com.himanshu.sarkari_yojna.settings.ui.select_language

import android.annotation.SuppressLint
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.himanshu.sarkari_yojna.settings.databinding.RecyclerItemLanguageBinding
import com.himanshu.sarkari_yojna.settings.ui.select_language.model.LanguagePresentationModel
import com.himanshu.sarkariyojna.android_base.language.Language

class LanguageAdapter(
     private val languageItemClickListener: LanguageItemClickListener
) : RecyclerView.Adapter<LanguageAdapter.LanguageViewHolder>() {

    private var languages: List<LanguagePresentationModel> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setLanguages(
        languages: List<LanguagePresentationModel>
    ) {
        this.languages = languages
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LanguageViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(
        holder: LanguageViewHolder,
        position: Int
    ) {
        holder.bind(languages[position])
    }

    override fun getItemCount(): Int {
      return languages.size
    }

    inner class LanguageViewHolder(
        private val itemLanguageBinding: RecyclerItemLanguageBinding
    ) : RecyclerView.ViewHolder(
        itemLanguageBinding.root
    ),View.OnClickListener{

        init {
            itemLanguageBinding.root.setOnClickListener(this)
        }

        fun bind(
            language: LanguagePresentationModel
        ){
            itemLanguageBinding.language = language
        }

        override fun onClick(view: View?) {
            val selectedLanguage = languages[adapterPosition]
            languageItemClickListener.onLanguageClicked(selectedLanguage.language)
        }
    }

    interface LanguageItemClickListener {
        fun onLanguageClicked(language: Language)
    }
}