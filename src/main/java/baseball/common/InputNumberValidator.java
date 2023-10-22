package baseball.common;

public class InputNumberValidator {

    public static final String WRONG_INPUT_TYPE_MESSAGE = "1~9 사이의 숫자만 입력 가능합니다.";
    public static final String WRONG_INPUT_SIZE_MESSAGE = "세자리 숫자를 입력해주세요.";
    public static final String WRONG_INPUT_UNIQUE_MESSAGE = "서로 다른 숫자만 입력 가능합니다.";
    public static final int NUMBER_LENGTH = 3;
    private static final int NUMBER_RANGE_MAX = 9;
    public static final char NUMBER_RANGE_MIN_CHARACTER = '1';
    public static final char NUMBER_RANGE_MAX_CHARACTER = '9';

    static public void validate(String inputNumber) {
        //1. 숫자인지 확인
        //2. 세 자리인지 확인
        //3. 서로 다른 숫자인지 확인

        if(!isNumber(inputNumber)) {
            throw new IllegalArgumentException(WRONG_INPUT_TYPE_MESSAGE);
        }
        if(!isThreeLetters(inputNumber)) {
            throw new IllegalArgumentException(WRONG_INPUT_SIZE_MESSAGE);
        }
        if(!isUniqueCharacters(inputNumber)) {
            throw new IllegalArgumentException(WRONG_INPUT_UNIQUE_MESSAGE);
        }

    }

    private static boolean isNumber(String inputNumber) {
        for(int i = 0; i< inputNumber.length(); i++) {
            char number = inputNumber.charAt(i);
            if(number < NUMBER_RANGE_MIN_CHARACTER || number > NUMBER_RANGE_MAX_CHARACTER) {
                return false;
            }
        }

        return true;
    }

    private static boolean isThreeLetters(String inputNumber) {
        if(inputNumber.length() != NUMBER_LENGTH) return false;
        return true;
    }

    private static boolean isUniqueCharacters(String inputNumber) {
        boolean[] isContained = new boolean[NUMBER_RANGE_MAX+1];

        for(int i=0; i<inputNumber.length(); i++) {
            int number = Character.getNumericValue(inputNumber.charAt(i));
            if(isContained[number]) return false;
            isContained[number] = true;
        }

        return true;
    }

}
