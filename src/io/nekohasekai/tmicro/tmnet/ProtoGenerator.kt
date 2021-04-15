package io.nekohasekai.tmicro.tmnet

import cn.hutool.core.util.ClassUtil
import cn.hutool.core.util.ReflectUtil
import java.io.File
import java.lang.reflect.Modifier
import java.util.*

fun main(args: Array<String>) {

    val list = LinkedList<Class<out TMApi.Object>>()
    list += TMApi::class.java.classes.toList() as List<Class<out TMApi.Object>>
    list.reverse()

    val defs = mapOf(* TMClassDef::class.java.fields.filter {
        Modifier.isPublic(it.modifiers)
    }.map {
        it.name to ReflectUtil.getStaticFieldValue(it) as Int
    }.toTypedArray())

    val root = File("src/io/nekohasekai/tmicro/tmnet")
    val clientRoots = arrayOf(
        File("generated"),
        File("../nmd/src/io/nekohasekai/tmicro/tmnet"),
        File("../tm/app/src/main/java/io/nekohasekai/tmicro/tmnet")
    )

    val api = File(root, "TMApi.java")
    var apiSource = api.readText()

    for (def in defs) {
        apiSource = apiSource.replace("TMClassDef." + def.key, "0x${Integer.toHexString(def.value)}")
    }
    for (clientRoot in clientRoots) {
        File(clientRoot, api.name).writeText(apiSource)
    }

    var builder = ""

    for (clazz in list) {
        if (ClassUtil.isAbstract(clazz)) continue
        val instance = ReflectUtil.newInstance(clazz)

        builder += "case 0x${Integer.toHexString(instance.constructor)}:\n"
        builder += "    response = new TMApi.${clazz.simpleName}();\n"
        builder += "    break;\n"
    }

    val store = File(root, "TMStore.java")
    var storeSource = store.readText()
    storeSource = storeSource.substringBefore("switch (constructor) {") + "switch (constructor) {\n" +
            builder.split("\n").joinToString("\n") { "            $it" } +
            "default:\n                throw new" + storeSource.substringAfter("default:\n                throw new")

    store.writeText(storeSource)

    for (def in defs) {
        storeSource = storeSource.replace("TMClassDef." + def.key, "0x${Integer.toHexString(def.value)}")
    }

    for (clientRoot in clientRoots) {
        File(clientRoot, store.name).writeText(storeSource)
    }

}