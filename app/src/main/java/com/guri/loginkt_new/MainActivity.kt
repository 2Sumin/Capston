package com.guri.loginkt_new

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.guri.loginkt_new.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    // 뷰바인딩 사용하기 위해 추가
    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // binding 사용하기 위해 추가
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        // 로그인 버튼 누르면 홈화면으로 이동
        binding.btnRegister.setOnClickListener {
            val name = binding.ETName.text.toString()
            val username = binding.ETUserName.text.toString()
            val email = binding.ETEmailAddress.text.toString()
            val password = binding.ETPassword.text.toString()
            val university = binding.ETSchool.text.toString()
            val major = binding.ETGrade.text.toString()

            registerUser(email, password, name, username, university, major)
        }
    }

    private fun registerUser(email: String, password: String, name: String, username: String, university: String, major: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "회원가입 성공", Toast.LENGTH_SHORT).show()
                    val user = auth.currentUser

                    // Firebase Realtime Database에 추가 사용자 정보를 저장
                    val database = FirebaseDatabase.getInstance().getReference("Users")
                    val userId = user?.uid

                    val userData = hashMapOf(
                        "name" to name,
                        "username" to username,
                        "email" to email,
                        "university" to university,
                        "major" to major
                    )

                    userId?.let {
                        database.child(it).setValue(userData).addOnCompleteListener { dbTask ->
                            if (dbTask.isSuccessful) {
                                // 저장 성공 시 LoginActivity로 이동
                                val intent = Intent(this, LoginActivity::class.java)
                                startActivity(intent)
                            } else {
                                Toast.makeText(this, "데이터 저장 실패", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                } else {
                    Toast.makeText(baseContext, "회원가입 실패", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
