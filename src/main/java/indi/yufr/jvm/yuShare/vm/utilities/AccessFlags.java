//package indi.yufr.jvm.yuShare.vm.utilities;
//
//import lombok.Data;
//
///**
// * @date: 2022/1/17 18:02
// * @author: farui.yu
// */
//@Data
//public class AccessFlags {
//
//    public AccessFlags(long flags) {
//        this.flags = flags;
//    }
//
//    private long flags;
//
//    // Java access flags
//    public boolean isPublic      () { return (flags & JVM_ACC_PUBLIC      ) != 0; }
//    public boolean isPrivate     () { return (flags & JVM_ACC_PRIVATE     ) != 0; }
//    public boolean isProtected   () { return (flags & JVM_ACC_PROTECTED   ) != 0; }
//    public boolean isStatic      () { return (flags & JVM_ACC_STATIC      ) != 0; }
//    public boolean isFinal       () { return (flags & JVM_ACC_FINAL       ) != 0; }
//    public boolean isSynchronized() { return (flags & JVM_ACC_SYNCHRONIZED) != 0; }
//    public boolean isSuper       () { return (flags & JVM_ACC_SUPER       ) != 0; }
//    public boolean isVolatile    () { return (flags & JVM_ACC_VOLATILE    ) != 0; }
//    public boolean isBridge      () { return (flags & JVM_ACC_BRIDGE      ) != 0; }
//    public boolean isTransient   () { return (flags & JVM_ACC_TRANSIENT   ) != 0; }
//    public boolean isVarArgs     () { return (flags & JVM_ACC_VARARGS     ) != 0; }
//    public boolean isNative      () { return (flags & JVM_ACC_NATIVE      ) != 0; }
//    public boolean isEnum        () { return (flags & JVM_ACC_ENUM        ) != 0; }
//    public boolean isAnnotation  () { return (flags & JVM_ACC_ANNOTATION  ) != 0; }
//    public boolean isInterface   () { return (flags & JVM_ACC_INTERFACE   ) != 0; }
//    public boolean isAbstract    () { return (flags & JVM_ACC_ABSTRACT    ) != 0; }
//    public boolean isStrict      () { return (flags & JVM_ACC_STRICT      ) != 0; }
//    public boolean isSynthetic   () { return (flags & JVM_ACC_SYNTHETIC   ) != 0; }
//
//    public long getValue         () { return flags; }
//
//    // Hotspot internal flags
//    // Method* flags
//    public boolean isMonitorMatching   () { return (flags & JVM_ACC_MONITOR_MATCH          ) != 0; }
//    public boolean hasMonitorBytecodes () { return (flags & JVM_ACC_HAS_MONITOR_BYTECODES  ) != 0; }
//    public boolean hasLoops            () { return (flags & JVM_ACC_HAS_LOOPS              ) != 0; }
//    public boolean loopsFlagInit       () { return (flags & JVM_ACC_LOOPS_FLAG_INIT        ) != 0; }
//    public boolean queuedForCompilation() { return (flags & JVM_ACC_QUEUED                 ) != 0; }
//    public boolean isNotOsrCompilable  () { return (flags & JVM_ACC_NOT_OSR_COMPILABLE     ) != 0; }
//    public boolean hasLineNumberTable  () { return (flags & JVM_ACC_HAS_LINE_NUMBER_TABLE  ) != 0; }
//    public boolean hasCheckedExceptions() { return (flags & JVM_ACC_HAS_CHECKED_EXCEPTIONS ) != 0; }
//    public boolean hasJsrs             () { return (flags & JVM_ACC_HAS_JSRS               ) != 0; }
//    public boolean isObsolete          () { return (flags & JVM_ACC_IS_OBSOLETE            ) != 0; }
//
//    // Klass* flags
//    public boolean hasMirandaMethods    () { return (flags & JVM_ACC_HAS_MIRANDA_METHODS    ) != 0; }
//    public boolean hasVanillaConstructor() { return (flags & JVM_ACC_HAS_VANILLA_CONSTRUCTOR) != 0; }
//    public boolean hasFinalizer         () { return (flags & JVM_ACC_HAS_FINALIZER          ) != 0; }
//    public boolean isCloneable          () { return (flags & JVM_ACC_IS_CLONEABLE           ) != 0; }
//
//    // Klass* and Method* flags
//    public boolean hasLocalVariableTable() { return (flags & JVM_ACC_HAS_LOCAL_VARIABLE_TABLE ) != 0; }
//
//    // field flags
//    public boolean fieldAccessWatched () { return (flags & JVM_ACC_FIELD_ACCESS_WATCHED) != 0; }
//    public boolean fieldModificationWatched() { return (flags & JVM_ACC_FIELD_MODIFICATION_WATCHED) != 0; }
//    public boolean fieldHasGenericSignature() { return (flags & JVM_ACC_FIELD_HAS_GENERIC_SIGNATURE)!= 0; }
//
//}
