package com.dsumtsov.sku.composite.controller

import com.dsumtsov.sku.composite.dto.SkuCompositeDTO
import com.dsumtsov.sku.composite.service.SkuCompositeService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import mu.KotlinLogging
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

private val log = KotlinLogging.logger {}

@RestController
@RequestMapping("/sku-composite")
@Tag(name = "SKU Composite", description = "REST API for composite SKU information")
class SkuCompositeController(
    private val skuCompositeService: SkuCompositeService
) {

    @Operation(summary = "Returns a composite view of the specified SKU ID")
    @GetMapping("/{skuId}")
    @PreAuthorize("hasAuthority('SCOPE_sku:read')")
    fun getCompositeSku(@PathVariable skuId: Long): SkuCompositeDTO {
        log.info { "Request -> get composite sku by sku_id: $skuId" }
        return skuCompositeService.getCompositeSku(skuId)
    }

    @Operation(summary = "Creates a composite sku")
    @PostMapping("/create")
    @PreAuthorize("hasAuthority('SCOPE_sku:write')")
    fun createCompositeSku(@RequestBody skuCompositeDTO: SkuCompositeDTO): SkuCompositeDTO {
        log.info { "Request -> create composite sku: $skuCompositeDTO" }
        return skuCompositeService.createCompositeSku(skuCompositeDTO)
    }

    @Operation(summary = "Deletes a composite sku")
    @DeleteMapping("/{skuId}")
    @PreAuthorize("hasAuthority('SCOPE_sku:write')")
    fun deleteCompositeSku(@PathVariable skuId: Long) {
        log.info { "Request -> delete composite sku by sku_id: $skuId" }
        return skuCompositeService.deleteCompositeSku(skuId)
    }
}