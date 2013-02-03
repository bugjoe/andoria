/**
 * singleton configuration class.
 * @file configuration.cpp
 * @author Sandro Guldner
 * @version 1.0
 * @brief 6. November 2012
 */

#include "configuration.hpp"

Configuration* Configuration::m_PCfg = nullptr;

Configuration::Configuration()
{}

Configuration::~Configuration()
{}

Configuration* Configuration::GetInstance()
{
	if(Configuration::m_PCfg == nullptr)
	{
		Configuration::m_PCfg = new Configuration();
	}
	return Configuration::m_PCfg;
}

void Configuration::Init()
{}

void Configuration::Delete()
{
	delete Configuration::m_PCfg;
	Configuration::m_PCfg = nullptr;
}