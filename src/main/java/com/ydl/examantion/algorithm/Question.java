package com.ydl.examantion.algorithm;

import java.util.List;

public class Question {
	private Integer id;
	private String type;
	private Integer score;
	private Double difficulty;
	private List<Integer> knowledgePoints;
	
	public Question(Integer id, String type, Integer score, Double diffiulty, List<Integer> knowledgePoints) {
		super();
		this.id = id;
		this.type = type;
		this.score = score;
		this.difficulty = diffiulty;
		this.setKnowledgePoints(knowledgePoints);
	}
	
	public Question() { }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public double getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(double diffiulty) {
		this.difficulty = diffiulty;
	}

	public List<Integer> getKnowledgePoints() {
		return knowledgePoints;
	}

	public void setKnowledgePoints(List<Integer> knowledgePoints) {
		this.knowledgePoints = knowledgePoints;
	}

	@Override
	public String toString() {
		return "id:" + id + "--" 
			+  "题型：" + type + "--" 
			 + "分数：" + score + "--" 
			 + "难度：" + difficulty + "--" 
 			 + "知识点：" + this.getKnowledgePoints().get(0);
	}
	
	
}
