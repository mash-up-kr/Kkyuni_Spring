package com.example.ggyunispring.domain.repository

import com.example.ggyunispring.domain.entity.Logo
import org.springframework.data.repository.CrudRepository

interface LogoRepository: CrudRepository<Logo, Long> {
}