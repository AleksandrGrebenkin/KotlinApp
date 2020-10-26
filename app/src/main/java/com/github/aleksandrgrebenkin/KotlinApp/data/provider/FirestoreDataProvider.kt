package com.github.aleksandrgrebenkin.KotlinApp.data.provider

import androidx.lifecycle.MutableLiveData
import com.github.aleksandrgrebenkin.KotlinApp.data.entity.User
import com.github.aleksandrgrebenkin.KotlinApp.data.errors.NoAuthException
import com.github.aleksandrgrebenkin.KotlinApp.data.model.NoteResult
import com.github.aleksandrgrebenkin.KotlinApp.model.data.entity.Note
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreDataProvider : DataProvider {

    companion object {
        private const val NOTES_COLLECTION = "notes"
        private const val USERS_COLLECTION = "users"
    }

    private val store by lazy { FirebaseFirestore.getInstance() }
    private val notesReference
        get() = currentUser?.let {
            store.collection(USERS_COLLECTION).document(it.uid).collection(NOTES_COLLECTION)
        } ?: throw NoAuthException()
    private val currentUser
        get() = FirebaseAuth.getInstance().currentUser

    override fun getNotes() = MutableLiveData<NoteResult>().apply {
        notesReference.addSnapshotListener { snapshot, error ->
            error?.let {
                value = NoteResult.Error(it)
                return@addSnapshotListener
            }
            snapshot?.let {
                val notes = it.documents.map { it.toObject(Note::class.java) }
                value = NoteResult.Success(notes)
            }
        }
    }

    override fun saveNote(note: Note) = MutableLiveData<NoteResult>().apply {
        notesReference.document(note.id).set(note)
                .addOnSuccessListener {
                    value = NoteResult.Success(note)
                }.addOnFailureListener {
                    value = NoteResult.Error(it)
                }
    }

    override fun getNoteById(id: String) = MutableLiveData<NoteResult>().apply {
        notesReference.document(id).get()
                .addOnSuccessListener { snapshot ->
                    val note = snapshot.toObject(Note::class.java) as Note
                    value = NoteResult.Success(note)
                }.addOnFailureListener {
                    value = NoteResult.Error(it)
                }
    }

    override fun getCurrentUser() = MutableLiveData<User?>().apply {
        value = currentUser?.let { User(it.displayName ?: "", it.email ?: "") }
    }
}