package indi.yufr.jvm.share.vm.utilities;

public class BasicType {

    public static final BasicType T_BOOLEAN = new BasicType(4);
    public static final BasicType T_CHAR = new BasicType(5);
    public static final BasicType T_FLOAT = new BasicType(6);
    public static final BasicType T_DOUBLE = new BasicType(7);
    public static final BasicType T_BYTE = new BasicType(8);
    public static final BasicType T_SHORT = new BasicType(9);
    public static final BasicType T_INT = new BasicType(10);
    public static final BasicType T_LONG = new BasicType(11);
    public static final BasicType T_OBJECT = new BasicType(12);
    public static final BasicType T_ARRAY = new BasicType(13);
    public static final BasicType T_VOID = new BasicType(14);
    public static final BasicType T_ADDRESS = new BasicType(15);
    public static final BasicType T_NARROWOOP = new BasicType(16);
    public static final BasicType T_METADATA = new BasicType(17);
    public static final BasicType T_NARROWKLASS = new BasicType(18);
    public static final BasicType T_CONFLICT = new BasicType(19);
    public static final BasicType T_ILLEGAL = new BasicType(99);

    public static int getTBoolean() {
        return T_BOOLEAN.getType();
    }

    public static int getTChar() {
        return T_CHAR.getType();
    }

    public static int getTFloat() {
        return T_FLOAT.getType();
    }

    public static int getTDouble() {
        return T_DOUBLE.getType();
    }

    public static int getTByte() {
        return T_BYTE.getType();
    }

    public static int getTShort() {
        return T_SHORT.getType();
    }

    public static int getTInt() {
        return T_INT.getType();
    }

    public static int getTLong() {
        return T_LONG.getType();
    }

    public static int getTObject() {
        return T_OBJECT.getType();
    }

    public static int getTArray() {
        return T_ARRAY.getType();
    }

    public static int getTVoid() {
        return T_VOID.getType();
    }

    public static int getTAddress() {
        return T_ADDRESS.getType();
    }

    public static int getTNarrowOop() {
        return T_NARROWOOP.getType();
    }

    public static int getTMetadata() {
        return T_METADATA.getType();
    }

    public static int getTNarrowKlass() {
        return T_NARROWKLASS.getType();
    }

    /**
     * For stack value type with conflicting contents
     */
    public static int getTConflict() {
        return T_CONFLICT.getType();
    }

    public static int getTIllegal() {
        return T_ILLEGAL.getType();
    }

    public static BasicType intToBasicType(int i) {
        if (i == T_BOOLEAN.getType()) {
            return T_BOOLEAN;
        } else if (i == T_CHAR.getType()) {
            return T_CHAR;
        } else if (i == T_FLOAT.getType()) {
            return T_FLOAT;
        } else if (i == T_DOUBLE.getType()) {
            return T_DOUBLE;
        } else if (i == T_BYTE.getType()) {
            return T_BYTE;
        } else if (i == T_SHORT.getType()) {
            return T_SHORT;
        } else if (i == T_INT.getType()) {
            return T_INT;
        } else if (i == T_LONG.getType()) {
            return T_LONG;
        } else if (i == T_OBJECT.getType()) {
            return T_OBJECT;
        } else if (i == T_ARRAY.getType()) {
            return T_ARRAY;
        } else if (i == T_VOID.getType()) {
            return T_VOID;
        } else if (i == T_ADDRESS.getType()) {
            return T_ADDRESS;
        } else if (i == T_NARROWOOP.getType()) {
            return T_NARROWOOP;
        } else if (i == T_METADATA.getType()) {
            return T_METADATA;
        } else if (i == T_NARROWKLASS.getType()) {
            return T_NARROWKLASS;
        } else {
            return T_ILLEGAL;
        }
    }

    public static BasicType charToBasicType(char c) {
        switch (c) {
            case 'B':
                return T_BYTE;
            case 'C':
                return T_CHAR;
            case 'D':
                return T_DOUBLE;
            case 'F':
                return T_FLOAT;
            case 'I':
                return T_INT;
            case 'J':
                return T_LONG;
            case 'S':
                return T_SHORT;
            case 'Z':
                return T_BOOLEAN;
            case 'V':
                return T_VOID;
            case 'L':
                return T_OBJECT;
            case '[':
                return T_ARRAY;
        }
        return T_ILLEGAL;
    }

    public static int charToType(char c) {
        return charToBasicType(c).getType();
    }

    public String getName() {
        if (type == T_BOOLEAN.getType()) {
            return "boolean";
        } else if (type == T_CHAR.getType()) {
            return "char";
        } else if (type == T_FLOAT.getType()) {
            return "float";
        } else if (type == T_DOUBLE.getType()) {
            return "double";
        } else if (type == T_BYTE.getType()) {
            return "byte";
        } else if (type == T_SHORT.getType()) {
            return "short";
        } else if (type == T_INT.getType()) {
            return "int";
        } else if (type == T_LONG.getType()) {
            return "long";
        } else if (type == T_OBJECT.getType()) {
            return "object";
        } else if (type == T_ARRAY.getType()) {
            return "array";
        } else if (type == T_VOID.getType()) {
            return "void";
        } else if (type == T_ADDRESS.getType()) {
            return "address";
        } else if (type == T_NARROWOOP.getType()) {
            return "narrow oop";
        } else if (type == T_METADATA.getType()) {
            return "metadata";
        } else if (type == T_NARROWKLASS.getType()) {
            return "narrow klass";
        } else if (type == T_CONFLICT.getType()) {
            return "conflict";
        } else {
            return "ILLEGAL TYPE";
        }
    }

    //-- Internals only below this point
    private BasicType() {
    }

    private BasicType(int type) {
        this.type = type;
    }

    private int type;

    public int getType() {
        return type;
    }
}
