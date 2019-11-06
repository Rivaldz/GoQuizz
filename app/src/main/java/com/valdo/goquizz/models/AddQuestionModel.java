package com.valdo.goquizz.models;

public class AddQuestionModel {

//  private String question;
  private String answer1, answer2, answer3, answer4, trueAnswer ;

//  public String getQuestion() {
//    return question;
//  }

//  public AddQuestionModel(String s, String toString, String string, String s1, String toString1) {
//  }

//
//  public void setQuestion(String question) {
//    this.question = question;
//  }


  public String getTrueAnswer() {
    return trueAnswer;
  }

  public void setTrueAnswer(String trueAnswer) {
    this.trueAnswer = trueAnswer;
  }

  public String getAnswer1() {
    return answer1;
  }

  public void setAnswer1(String answer1) {
    this.answer1 = answer1;
  }

  public String getAnswer2() {
    return answer2;
  }

  public void setAnswer2(String answer2) {
    this.answer2 = answer2;
  }

  public String getAnswer3() {
    return answer3;
  }

  public void setAnswer3(String answer3) {
    this.answer3 = answer3;
  }

  public String getAnswer4() {
    return answer4;
  }

  public void setAnswer4(String answer4) {
    this.answer4 = answer4;
  }

  @Override
  public String toString() {
    return "AddQuestionModel{" +
            "answer1='" + answer1 + '\'' +
            ", answer2='" + answer2 + '\'' +
            ", answer3='" + answer3 + '\'' +
            ", answer4='" + answer4 + '\'' +
            ", trueAnswer='" + trueAnswer + '\'' +
            '}';
  }

  public AddQuestionModel(String answer1, String answer2, String answer3, String answer4, String trueAnswer) {
//    this.question = question;
    this.answer1 = answer1;
    this.answer2 = answer2;
    this.answer3 = answer3;
    this.answer4 = answer4;
    this.trueAnswer = trueAnswer;
  }

//
//  @Override
//  public String toString() {
//    return "AddQuestionModel{" +
//            "answer1='" + answer1 + '\'' +
//            ", answer2='" + answer2 + '\'' +
//            ", answer3='" + answer3 + '\'' +
//            ", answer4='" + answer4 + '\'' +
//            '}';
//  }
//
//  public AddQuestionModel(String answer1, String answer2, String answer3, String answer4) {
//    this.answer1 = answer1;
//    this.answer2 = answer2;
//    this.answer3 = answer3;
//    this.answer4 = answer4;
//  }
}
