package Broker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import Common.IpNode;
import Common.Message;
import Common.Topic;

public class Filter {
	List<IpNode> index;//索引
	public Filter(List<IpNode> index) {
		this.index = index;
	}
	public HashMap<IpNode, List<Message>> filter(List<Message> list) {
		//将Message按照分发地址分类
		HashMap<IpNode, List<Message>> map = new HashMap<IpNode, List<Message>>();
		//初始化
		for(IpNode address:index) {
			if(map.get(address)==null) {
				map.put(address, new ArrayList<Message>());
			}
		}
		//遍历消息，将每条message分类
		Iterator<Message> iterator = list.iterator();
		while(iterator.hasNext()) {
			Message message = iterator.next();
			//每个message可能有很多消费者
			List<IpNode> consumer_address = message.getTopic().getConsumer();
			Iterator<IpNode> it = consumer_address.iterator();
			while(it.hasNext()) {
				IpNode address = it.next();
				List<Message> l = map.get(address);
				if(l!=null)
					l.add(message);
			}
		}
		return map;
	}

}
