package com.training.belajarfirebase

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class SignUpActivity : AppCompatActivity() {

    private var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        //inisialisasi firebase authentikasi
        mAuth = FirebaseAuth.getInstance();
        initview()
    }

    private fun initview() {
        //aksi ketika tombol button register di klik
        //cek semua inputan wajib diisi
        if (register_email.text.toString() == "" &&
                register_name.text.toString() == "" &&
                register_password.text.toString() == "" &&
                register_phone.text.toString() == "" &&
                register_repassword.text.toString() == "") {
            toast(getString(R.string.tidakbolehkosong))
        }
        //cek password dan confirm password sama atau tidak
        else if (register_password.text.toString() !=
                register_repassword.text.toString()) {
            toast(getString(R.string.passtidaksama))
        }
        //cek minimum password
        else if (register_password.text.toString().length < 6) {
            toast(getString(R.string.batasan_password))
        } else {
            prosessignup()
        }

    }

    //proses signup dari firebase auth
    private fun prosessignup() {
        mAuth!!.createUserWithEmailAndPassword(register_email.text.toString(), register_password.text.toString()).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                val user = mAuth!!.currentUser
//        actionsavedatabase()
                startActivity<LoginActivity>()
            }

        }
    }
}



//    private fun actionsavedatabase() {
//
//    }

