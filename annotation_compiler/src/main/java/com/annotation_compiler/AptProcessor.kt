package com.annotation_compiler

import com.atar.annotations.CreateService
import com.google.auto.service.AutoService
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.TypeSpec
import retrofit2.Retrofit
import javax.annotation.processing.*
import javax.inject.Inject
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.ElementKind
import javax.lang.model.element.PackageElement
import javax.lang.model.element.TypeElement
import javax.lang.model.util.Elements
import javax.tools.Diagnostic

@AutoService(Processor::class)
class AptProcessor : AbstractProcessor() {

    private var mFiler: Filer? = null

    private var mElementUtils: Elements? = null

    override fun init(processingEnv: ProcessingEnvironment?) {
        super.init(processingEnv)
        mFiler = processingEnv?.filer
        mElementUtils = processingEnv?.elementUtils
    }

    //指定处理的版本
    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.latestSupported()
    }

    //给到需要处理的注解
    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        val types: LinkedHashSet<String> = LinkedHashSet()
        getSupportedAnnotations().forEach { clazz: Class<out Annotation> ->
            types.add(clazz.canonicalName)
        }
        return types
    }

    private fun getSupportedAnnotations(): Set<Class<out Annotation>> {
        val annotations: LinkedHashSet<Class<out Annotation>> = LinkedHashSet()
        // 需要解析的自定义注解
        annotations.add(CreateService::class.java)
        return annotations
    }

    /**
    KotlinPoet 官方helloWorld示例：
    val greeterClass = ClassName("", "Greeter")
    val file = FileSpec.builder("", "HelloWorld")
    .addType(TypeSpec.classBuilder("Greeter")
    .primaryConstructor(FunSpec.constructorBuilder()
    .addParameter("name", String::class).build())
    .addProperty(PropertySpec.builder("name", String::class)
    .initializer("name").build())
    .addFunction(FunSpec.builder("greet")
    .addStatement("println(%P)", "Hello, \$name").build())
    .build())
    .addFunction(FunSpec.builder("main")
    .addParameter("args", String::class, VARARG)
    .addStatement("%T(args[0]).greet()", greeterClass).build())
    .build()
    file.writeTo(System.out)
    ——————————————————————————————————
    class Greeter(val name: String) {
    fun greet() {println("""Hello, $name""")}}
    fun main(vararg args: String) {Greeter(args[0]).greet()}
     */
    override fun process(annotations: MutableSet<out TypeElement>, roundEnvironment: RoundEnvironment): Boolean {
        val elementsAnnotatedWith: Set<out Element> = roundEnvironment.getElementsAnnotatedWith(CreateService::class.java);
        log("className")

//        var map: Map<String, List<ExecutableElement>> = mutableMapOf()
//        elementsAnnotatedWith.forEach {
//            val elemen: ExecutableElement = it as ExecutableElement
//            val className = elemen.enclosingElement.simpleName
////            map.get(className)
////            log("it: ${(elemen.simpleName.toString())}")
////            var list = elemen.typeParameters
////            log("fang fa ming: ${elemen.simpleName}")//注解的方法名称
////            log("classname: ${elemen.enclosingElement.simpleName}")//得到注解方法的类
////            log("getTypeParameters: ${(elemen.typeParameters.toString())}")//参数类型
////            log("getReturnType: ${(elemen.getReturnType().toString())}")//返回值
////            log("getParameters: ${(elemen.getParameters().toString())}") //参数名称
////            log("getThrownTypes: ${(elemen.getThrownTypes().toString())}")
////            val variableElement = it as VariableElement
//        }


        elementsAnnotatedWith.forEach { element ->
//            //得到包名
////            var packageName = processingEnv.getElementUtils().getPackageOf(element).toString();
            var e = element
            while (e.kind != ElementKind.PACKAGE) {
//                log("enclosingElement:${e.enclosingElement}")
                e = e.enclosingElement
            }
            var packageName = (e as PackageElement).toString()
            val className = element.getSimpleName().toString()


            var greeterClass = "${className}Impl";

            val superClassName = ClassName("com.example.myapplication.base.activity", "BaseRepository")
            val superInterfaceClassName = ClassName(packageName, className)
            try {

                log("8:${superInterfaceClassName.simpleNames.toString()}")
            } catch (e: Exception) {
                log("Exception ${e.message} \n")
//                e.printStackTrace()
            }
            val newSuperClassName = superClassName.parameterizedBy(superInterfaceClassName)
            val file = FileSpec.builder(packageName, greeterClass)
                .addType(
                    TypeSpec.classBuilder(greeterClass)//类名
                        .primaryConstructor(//本类默认构造函数
                            FunSpec.constructorBuilder()
                                .addParameter("retrofit", Retrofit::class)//构造函数里面参数
                                .addAnnotation(Inject::class.java)//构造函数加注解
                                .build()
                        ).superclass(newSuperClassName)//继承的父类
                        .addSuperclassConstructorParameter("retrofit", Retrofit::class)//父类构造函数参数
//                        .addSuperinterface(superInterfaceClassName)//父类实现接口

//                        .addProperty(
//                            PropertySpec.builder("name", String::class)
//                                .initializer("name").build()
//                        )
                        .addFunction(
                            FunSpec.builder("greet")
                                .addStatement("println(%P)", "Hello, ").build()
                        )
                        .build()
                )


//                .addFunction(
//                    FunSpec.builder("main")
//                        .addParameter("args", String::class, KModifier.VARARG)
//                        .addStatement("%T(args[0]).greet()", greeterClass).build()
//                )
                .build()
            mFiler?.let { filer -> file.writeTo(filer) }

        }
        return true
    }

    private fun log(message: String) {
        processingEnv.messager.printMessage(Diagnostic.Kind.NOTE, message)
    }
}