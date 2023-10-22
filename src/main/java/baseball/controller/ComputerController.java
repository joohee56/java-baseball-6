package baseball.controller;

import baseball.common.RandomUtility;
import baseball.model.BallAndStrikeCount;
import baseball.model.ComputerNumber;
import baseball.view.InputView;
import baseball.view.OutputView;
import java.util.List;

public class ComputerController {
    public static final String BALL_MESSAGE = "볼";
    public static final String SPACE_MESSAGE = " ";
    public static final String BLANK_MESSAGE = "";
    public static final String STRIKE_MESSAGE = "스트라이크";
    public static final String NOTHING_MESSAGE = "낫싱";
    public static final int SUCCESS_GAME_STRIKE_NUMBER = 3;

    ComputerNumber computerNumber;
    OutputView outputView = new OutputView();
    InputView inputView = new InputView();

    public void startGame() {
        initComputerNumber();
        proceedGame();
    }

    public void initComputerNumber() {
        computerNumber = new ComputerNumber(RandomUtility.createComputerNumber());
    }

    public void proceedGame() {
        outputView.printEnterNumber();
        String inputNumber = inputView.enterNumber();
        //TODO: 입력값 검증 (잘못된 값을 입력할 경우, IllegalArgumentException 발생)
        BallAndStrikeCount count = getBallAndStrikeCount(computerNumber, inputNumber);
        outputView.printHint(getHint(count));
        if(count.getBallCount() == SUCCESS_GAME_STRIKE_NUMBER) {
            System.out.println("맞았습니다!");
        } else {
            System.out.println("틀렸습니다!");
        }
    }

    //TODO: 테스트코드 작성
    public String getHint(BallAndStrikeCount count) {
        String Hint="";
        Hint += getBallHint(count.getBallCount());
        Hint += getStrikeHint(count.getStrikeCount());
        Hint += getNothingHint(count.getStrikeCount(), count.getBallCount());
        return Hint;
    }

    private String getStrikeHint(int strikeCount) {
        if(strikeCount > 0) {
            return strikeCount + STRIKE_MESSAGE + SPACE_MESSAGE;
        }

        return BLANK_MESSAGE;
    }

    private String getBallHint(int ballCount) {
        if(ballCount > 0) {
            return ballCount + BALL_MESSAGE + SPACE_MESSAGE;
        }
        return BLANK_MESSAGE;
    }

    private String getNothingHint(int strikeCount, int ballCount) {
        if(strikeCount == 0 && ballCount == 0) {
            return NOTHING_MESSAGE;
        }

        return BLANK_MESSAGE;
    }

    public BallAndStrikeCount getBallAndStrikeCount(ComputerNumber computerNumber, String inputNumber) {
        int ballCount = 0, strikeCount = 0;
        int[] computerNumberIndex = computerNumber.getNumberIndex();

        for(int i=0; i<inputNumber.length(); i++) {
            int num = Character.getNumericValue(inputNumber.charAt(i));

            if(computerNumberIndex[num] == 0) continue;
            else if(computerNumberIndex[num] == i+1) {
                strikeCount++;
            } else {
                ballCount++;
            }
        }

        return new BallAndStrikeCount(ballCount, strikeCount);
    }
}
