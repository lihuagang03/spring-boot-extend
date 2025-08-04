package com.spring.boot.ai.ollama;

import org.junit.jupiter.api.Test;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

/**
 * Test of {@link OllamaChatModel}
 *
 * @author lihuagang
 * @since 2025/2/6
 */
@SpringBootTest(classes = AiOllamaApplication.class)
public class AiOllamaTest {
	@Autowired
	private OllamaChatModel ollamaChatModel;

	/**
	 * <think>
	 * 好，用户给了一段英文，让我翻译成中文。首先，我需要通读这段文字，理解其内容和主要意思。
	 *
	 * 原文提到“Ollama”现在支持常用模型如Llama 3.1的工具调用功能。这句话的意思是说Ollama软件现在支持常用的模型Llama 3.1进行常见工具调用的功能。然后进一步解释了，这个功能可以让模型在遇到需要特定工具来解决的问题时，能够主动搜索和使用已知的工具库，从而实现更复杂的任务或与外界交互。
	 *
	 * 接下来，我要确保翻译准确传达原文的意思。同时，要保持语言流畅自然，避免直译导致的生硬感。例如，“now supports tool calling”可以翻译为“支持常用模型如Llama 3.1的工具调用功能。”；“making it possible for models to perform more complex tasks or interact with the outside world.”则需要准确传达出模型在处理复杂任务或与外界交互方面的潜力。
	 *
	 * 在翻译过程中，还要注意专业术语，比如“tool calling”翻译成“工具调用”是比较合适的。“popular models”可以译为“常用模型”，这样既保留了原意，又符合中文表达习惯。另外，“supporting tool(s) it knows about”中的“tool(s)”是复数形式，这里也要保持一致。
	 *
	 * 完成初稿后，我会通读一遍，检查是否有遗漏或不通顺的地方。确保翻译不仅准确传达原文的信息，也使读者能够理解Ollama软件在这一功能上的优势和应用前景。
	 *
	 * 最后，根据用户的需求，如果他们希望翻译成英文，我可以再进行一次完整的翻译，确保符合他们的使用场景。
	 * </think>
	 *
	 * Ollama 现代支持 Llama 3.1 这些常用模型的工具调用功能。这意味着 Ollama 软件能够基于其已知的知识库和工具列表（如 Llama），在遇到需要特定工具来解决的问题时，能够主动搜索并使用这些工具库中的资源，从而实现更复杂的任务或与外界交互的能力。
	 *
	 * 这段翻译准确传达了原文的意思：Ollama 现代支持 Llama 3.1 这些常用模型的工具调用功能。
	 */
	@Test
	void chatModel() {
		String prompt = """
                你是一个精通中文和英文的翻译大师。如果我给你英文就翻译成中文，给你中文就翻译成英文。
                """;
		String message = """
                Ollama now supports tool calling with popular models such as Llama 3.1.
                This enables a model to answer a given prompt using tool(s) it knows about,
                making it possible for models to perform more complex tasks or interact with the outside world.
                """;

		StopWatch stopWatch = new StopWatch("chatModel");
		stopWatch.start();

		String result = ollamaChatModel.call(prompt + ":" + message);

		stopWatch.stop();
		System.out.println(result);
		// StopWatch 'chatModel': 7.106078167 seconds; [] took 7.106078167 seconds = 100%
		System.out.println(stopWatch);
	}

	/**
	 * <think>
	 * 好，我现在需要设计一个实现送礼功能的电商平台架构。首先，我得明确用户的使用场景：他们下单购买商品后，不需要指定地址，而是通过朋友传递订单，这样可以减少信息过载，提高用户体验。
	 *
	 * 接下来，考虑整个系统的结构。系统应该包含几个模块，比如订单管理、配送服务、配送跟踪和数据分析等。这些模块需要与支付系统集成，确保订单正确处理。
	 *
	 * 首先，订单管理模块是基础部分。当用户下单购买后，订单数据需要存储起来，并且支持批量添加或删除订单。同时，订单中的商品信息也很重要，包括商品名称、价格、库存情况等等。另外，订单的状态也需要跟踪，比如在配送前的待配、配送中和配送完成等阶段。
	 *
	 * 然后是配送服务模块。这里需要一个强大的配送引擎来管理订单路径。这个引擎应该能够自动计算最短路径，处理多地址情况，并且有清晰的标识符，方便追踪订单。配送引擎可能需要API接口，这样当用户朋友填写地址后，系统可以自动连接到平台上的导航服务，生成配送路线。
	 *
	 * 接下来是配送跟踪模块。这里需要一个实时追踪系统，能够根据用户的指示更新订单的当前位置。这可能会包括使用GPS设备或者移动终端设备来获取当前位置信息，并将最新的位置数据发送给配送中心。此外，用户也需要反馈他们的位置和时间，这样平台才能及时更新配送状态。
	 *
	 * 数据分析与优化模块也很重要。通过分析用户的行为，比如下单频率和配送情况，可以改进算法的效率，提升用户体验。同时，了解用户的满意度和物流问题可以帮助优化送礼功能，确保更多的用户能够顺利使用。
	 *
	 * 最后是支付系统和结算模块。支付方面，需要实现订单在不指定地址的情况下进行管理，并且处理退款等问题。结算部分需要将订单金额发送到正确的配送中心账户，确保用户的账单及时完成。
	 *
	 * 在架构设计上，整体系统应该是一个高度可扩展的分布式系统，利用容器化技术来增加服务负载，提升可用性。各个模块之间要通过友好的API接口和良好的数据同步机制连接起来，比如使用数据库管理器（DBM）实现数据同步，确保订单和支付信息的一致性和完整性。
	 *
	 * 我还需要考虑平台的安全性和隐私保护，确保用户的信息不被泄露或滥用。同时，在售后服务方面，提供及时的响应时间和响应方式，让用户在出现问题时能够快速得到帮助。
	 *
	 * 总结一下，整个系统的架构应该包括一个模块化的结构，覆盖从订单管理到配送跟踪再到数据分析和支付结算的各个部分。每个模块需要与其他功能紧密连接，确保流畅的操作流程。这样设计出来的系统不仅满足送礼用户的预期需求，还能提升整体平台的用户体验和业务运营效率。
	 * </think>
	 *
	 * 为了实现“送礼”功能，我们设计了一个成熟的电商平台架构，以下是详细的设计步骤：
	 *
	 * ### 1. 系统目标与核心模块
	 * - **目标**：构建一个支持用户直接通过朋友传递订单的平台。
	 * - **核心模块**：
	 *   - **订单管理（Orders）**：存储商品、地址和支付信息。
	 *   - **配送服务（配送引擎）**：计算最短路径，生成配送路线。
	 *   - **配送跟踪（Tracking）**：实时更新订单位置。
	 *   - **结算与支付（Payment）**：处理订单不指定地址的配送问题，并支持退款。
	 *
	 * ### 2. 分布式架构设计
	 * - **分布式系统**：采用分布式部署，每个模块独立运行，确保高可用性和恢复性。
	 * - **容器化技术**：利用Flask和Docker实现高并发处理能力。
	 *
	 * ### 3. 订单管理模块（Orders）
	 * - **数据存储**：使用数据库（如MySQL）存储订单信息。
	 * - **订单状态管理**：
	 *   - **添加订单**：根据商品分类创建订单，记录商品信息、库存状态。
	 *   - **删除订单**：允许用户或平台处理，但不允许自动删除。
	 *   - **订单更新**：支持修改商品信息和库存情况。
	 *
	 * ### 4. 配送引擎（DeliveryEngine）
	 * - **路径计算**：利用Dijkstra算法计算最短路径，并生成配送标识符。
	 * - **多地址管理**：支持同时接收多个配送请求，自动合并地址信息。
	 *
	 * ### 5. 配送跟踪模块（Tracking）
	 * - **实时更新**：根据用户的指示更新订单位置。
	 * - **消息通知系统（MASN）**：使用消息通知系统来更新订单状态和位置。
	 *
	 * ### 6. 数据同步与存储
	 * - **数据库管理器（DBM）**：实现订单、支付信息的同步，确保数据一致性。
	 * - **实时响应机制**：及时接收用户的反馈并重新计算配送路线。
	 *
	 * ### 7. 支付与结算模块（Payment & Payment System）
	 * - **不指定地址下单**：支持用户通过朋友传递订单，避免用户填写地址。
	 * - **支付处理**：使用网络接口直接与配送中心进行转账或支付操作。
	 * - **退款机制**：提供多种退款选项，如金额抵扣、商品取消。
	 *
	 * ### 8. 用户界面与交互
	 * - **友情传递（Friendship）**：设置友情权限，允许用户连接朋友进行订单分享。
	 * - **智能反馈**：在反馈位置时及时显示用户的地理位置和时间信息。
	 *
	 * ### 9. 效率优化
	 * - **路径算法**：使用已有的最佳配送引擎，提升配送效率。
	 * - **数据同步机制**：实时更新订单状态，减少延迟。
	 *
	 * ### 10. 技术选择
	 * - **分布式存储**：使用数据库（如MySQL或MongoDB）进行分布式存储。
	 * - **容器化技术**：采用Flask和Docker实现高并发处理能力。
	 * - **消息通知系统（MASN）**：用于订单状态和位置的实时更新。
	 *
	 * ### 11. 预留功能
	 * - **预留订单**：允许用户在配送过程中保留订单，以便后续处理。
	 *
	 * ### 总结
	 * 通过以上设计，我们构建了一个能够支持用户直接通过朋友传递订单的平台。系统采用分布式架构，每个模块独立运行，确保高可用性和良好的用户体验。同时，使用先进的技术（如Flask、Docker和MASN）提升了系统的稳定性和响应速度，最终实现送礼功能的完善与优化。
	 */
	@Test
	void giftFunction() {
		String prompt = """
                你是一个资深的熟悉电商业务技术系统设计的架构师，请你在一个成熟的电商平台上实现一个送礼功能，
                即送礼用户下单购买一个商品时，不需要填写地址，送礼用户完成支付后把这个订单分享给另外一个人，
                比如他的朋友，由他的朋友来填写地址，然后这个订单才算完成，并开始发货履约。
                """;

		StopWatch stopWatch = new StopWatch("giftFunction");
		stopWatch.start();

		String result = ollamaChatModel.call(prompt);

		stopWatch.stop();
		System.out.println(result);
		// StopWatch 'giftFunction': 26.455756167 seconds; [] took 26.455756167 seconds = 100%
		System.out.println(stopWatch);
	}
}
