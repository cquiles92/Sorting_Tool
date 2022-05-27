import java.util.function.LongUnaryOperator;

class Operator {

    public static LongUnaryOperator unaryOperator = x -> x % 2 == 1 ? x + 1 : x + 2;
}