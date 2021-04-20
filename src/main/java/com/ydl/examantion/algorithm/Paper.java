package com.ydl.examantion.algorithm;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Paper {
	private int id;
	private int totalScore;
	private List<Integer> knowledgePoints;
	private double difficulty;
	private Map<String,Integer> typeCountMapping;
	private Map<String,Integer> typeScoreMapping;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTotalScore() {
		int totalScore = 0;
		Set<String> keySet= typeScoreMapping.keySet();
		for(String key:keySet){
			totalScore += typeScoreMapping.get(key);
		}
		return totalScore;
	}
	
	public double getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(double difficulty) {
		this.difficulty = difficulty;
	}
	public Map<String, Integer> getTypeCountMapping() {
		return typeCountMapping;
	}
	public void setTypeCountMapping(Map<String, Integer> typeCountMapping) {
		this.typeCountMapping = typeCountMapping;
	}
	
	public int getQuestionCount() {
		int questionCount = 0;
		Set<String> types = typeCountMapping.keySet();
		for(String type : types) {
			questionCount += typeCountMapping.get(type);
		}
		return questionCount;
	}
	public List<Integer> getKnowledgePoints() {
		return knowledgePoints;
	}
	public void setKnowledgePoints(List<Integer> knowledgePoints) {
		this.knowledgePoints = knowledgePoints;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("-------试卷-------");
		sb.append("\n总分：" + this.getTotalScore());
		sb.append("\n֪知识点");
		for(Integer kp : knowledgePoints) {
			sb.append(kp);
			sb.append(" ");
		}
		sb.append("\n难度：" + difficulty);
		sb.append("\n题型");
		Set<String> keys = typeCountMapping.keySet();
		for(String key : keys) {
			sb.append(key);
			sb.append(":");
			sb.append(typeCountMapping.get(key));
			sb.append("   ");
		}
		return sb.toString(); 
	}
	public Map<String,Integer> getTypeScoreMapping() {
		return typeScoreMapping;
	}
	public void setTypeScoreMapping(Map<String,Integer> typeScoreMapping) {
		this.typeScoreMapping = typeScoreMapping;
	}
}
